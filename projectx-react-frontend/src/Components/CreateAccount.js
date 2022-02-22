import TextField from "@mui/material/TextField";
import Grid from "@mui/material/Grid";
import ButtonGroup from "@mui/material/ButtonGroup";
import Button from "@mui/material/Button";
import { useState } from "react";
import { Link } from "react-router-dom";
import Radio from "@mui/material/Radio";
import RadioGroup from "@mui/material/RadioGroup";
import FormControlLabel from "@mui/material/FormControlLabel";
import FormControl from "@mui/material/FormControl";
import Tooltip from "@mui/material/Tooltip";
import { useNavigate } from "react-router-dom";

function CreateAccount() {
  const [password, setPassword] = useState("");
  const [username, setUsername] = useState("");
  const [firstname, setFirstname] = useState("");
  const [lastname, setLastname] = useState("");
  const [userType, setUserType] = useState("");
  const [error, setError] = useState("");
  const [passwordError, setPasswordError] = useState(false);


  let navigate = useNavigate();

  function handleSignup(e) {
    e.preventDefault();
    console.log("CLICKED CREATE ACCOUNT");
    if(passwordError){
      setError("fix password")
      return
    }
    let body={}

    if(userType==="merchant"){
      body= JSON.stringify({
        id: 0,
        merchantUsername: username,
        merchantPassword: password,
        firstName: firstname,
        lastName: lastname,
      })
    }else if(userType==="customer"){
      body= JSON.stringify({
        id: 0,
        customerUsername: username,
        customerPassword: password,
        firstName: firstname,
        lastName: lastname,
      })
    }else{
      setError("select user type")
    }
    fetch(`http://localhost:8080/api/add/${userType}`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body:body
      })
      // .then((res) => res.json())
      .then((res, error) => {
        console.log(error);
        if (res.status != 200) {
          setError("Error creating account");
        }else{
          navigate("/login")
        }
        console.log(res);
      });
  }

  return (
    <div className="createAccount">
      <Grid
        container
        spacing={0}
        direction="column"
        alignItems="center"
        justifyContent="center"
        style={{ minHeight: "100vh" }}
      >
              <span style={{color:"red"}} display={error !== ""}> {error} </span>

        <Grid item xs={3} spacing={50}>
          <Grid>
            <h1> Create An Account</h1>
          </Grid>
          <form action="login">
            <Grid item>
              <TextField
                id="standard-basic"
                label="Username"
                variant="standard"
                value={username}
                onChange={(event) => {
                  setUsername(event.target.value);
                }}
              />
            </Grid>
            <Grid item>
              <Tooltip title="Password must include one uppercase, one lowercase, a number, and minimum 8 characters.">
                <TextField
                  id="standard-basic"
                  label="Password"
                  type="password"
                  variant="standard"
                  value={password}
                  error={passwordError}
                  helperText={passwordError ? "Invalid Password" : ""}
                  onChange={(event) => {
                    setPassword(event.target.value);
                    if (
                      !event.target.value.match(
                        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$"
                      )
                    ) {
                      setPasswordError(true);
                    } else {
                      setPasswordError(false);
                    }
                  }}
                />
              </Tooltip>
            </Grid>
            <Grid item>
              <TextField
                id="standard-basic"
                label="First Name"
                variant="standard"
                value={firstname}
                onChange={(event) => {
                  setFirstname(event.target.value);
                }}
              />
            </Grid>
            <Grid item>
              <TextField
                id="standard-basic"
                label="Last Name"
                variant="standard"
                value={lastname}
                onChange={(event) => {
                  setLastname(event.target.value);
                }}
              />
            </Grid>
            <Grid item>
              <FormControl>
                <RadioGroup
                  row
                  aria-labelledby="demo-row-radio-buttons-group-label"
                  name="row-radio-buttons-group"
                  value={userType}
                  onChange={(e) => setUserType(e.target.value)}
                >
                  <FormControlLabel
                    value="customer"
                    control={<Radio />}
                    label="Customer"
                  />
                  <FormControlLabel
                    value="merchant"
                    control={<Radio />}
                    label="Merchant"
                  />
                </RadioGroup>
              </FormControl>
            </Grid>
          </form>
        </Grid>
        <ButtonGroup
          variant="contained"
          aria-label="outlined primary button group"
        >
          <Link to="/login">
            <Button>Go Back</Button>
          </Link>
          <Link to="/login">
            <Button onClick={handleSignup}>Create Account</Button>
          </Link>
        </ButtonGroup>
      </Grid>
    </div>
  );
}

export default CreateAccount;
