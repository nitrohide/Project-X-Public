import React, {useState} from 'react';
import { Navbar, Nav, Form, FormControl, Button, Row } from 'react-bootstrap';
import FormModal from './FormModal';
import { Link } from "react-router-dom";


const SiteNav = (props) => {
  // State contains values for search bar to pass back up to App
  const [searchValue, setSearchValue] = useState('');

  // State changes logged to pass back up to app when search button is clicked
  const handleSearch = (evt) => {
    evt.preventDefault();
    props.search(searchValue, true);
  }

  console.log(searchValue);

  return (
    <Navbar bg="dark" variant="dark" sticky='top'>
      <Navbar.Brand href="#home">Marketplace</Navbar.Brand>
      <Nav className="mr-auto">
        <Nav.Link href="#">Home</Nav.Link>
        <Link to="/login">
            <Button>Log out</Button>
        </Link>
      </Nav>

      <FormModal type ='create'/>
      <Form inline onSubmit={handleSearch}>
        <FormControl  type="text" placeholder="Search products" className="mr-sm-2" onChange={e=>setSearchValue(e.target.value)} />
        <Button variant="outline-info" type='submit'>Search</Button>
      </Form>

    </Navbar>
  );
}

export default SiteNav;


