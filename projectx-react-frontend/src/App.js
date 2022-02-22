import React, {useState} from 'react';
import './App.css';
import CardInterface from './Components/CardInterface';
import SiteNav from './Components/SiteNav';


// Root Page Module - has Navbar, search functions, and Card Pages
function App() {

  // States for searching - if applicable
  const [search, setSearch] = useState('');
  const [searching, setSearching] = useState(false);

  // Function to control searching views
  const searchFor = (searchValue, searchState) => {
    setSearch(searchValue);
    setSearching(searchState);
  }

  return (
    <div className="container-fluid">
      <SiteNav search={searchFor}/>
      {!searching && <CardInterface/>}
      {searching && <CardInterface search={search} searchFor={searchFor}/>}
    </div>
  );
}

export default App;
