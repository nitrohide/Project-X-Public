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
import { useNavigate } from "react-router-dom";


function Login() {
  const [password, setPassword] = useState("");
  const [username, setUsername] = useState("");
  const [userType, setUserType] = useState("");
  const [error, setError] = useState("");
  
  let navigate = useNavigate();

  function handleLogin(e) {
    e.preventDefault();
    console.log("CLICKED LOGIN");

    fetch(`http://localhost:8080/api/login/${userType}`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ username: username, password: password }),
    })
      .then((res) => res.json())
      .then((res, error) => {
        console.log(res);
        if (res.message == "invalid login") {
          setError("Error logging in");
          console.log("first if")
        }else{
          console.log("second if")
          navigate("/index")
        }
      });
  }

  return (
    <div className="Login">
      <Grid
        container
        spacing={0}
        direction="column"
        alignItems="center"
        justifyContent="center"
        style={{ minHeight: "100vh" }}
      >
        <Grid item >
          <Grid>
            <h1>Please Login</h1>
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
              <TextField
                id="standard-basic"
                label="Password"
                type="password"
                variant="standard"
                value={password}
                onChange={(event) => {
                  setPassword(event.target.value);
                }}
              />
            </Grid>
          </form>
        </Grid>
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
        <ButtonGroup
          variant="contained"
          aria-label="outlined primary button group"
        >
          <Button onClick={handleLogin}>Login</Button>

          <Link to="/CreateAccount">
            <Button>Sign up</Button>
          </Link>
          
        </ButtonGroup>

      </Grid>
    </div>
  );
}

export default Login;
