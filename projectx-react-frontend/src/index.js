import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import reportWebVitals from './reportWebVitals';
import {BrowserRouter, Routes, Route, Navigate} from 'react-router-dom'
import CreateAccount from './Components/CreateAccount'
import Login from './Components/Login'
import App from './App.js';

ReactDOM.render(
  <BrowserRouter>
  <Routes >
      <Route path="/" element={<Navigate replace to="/login" />} />
      <Route path="/login" element={<Login />}/>
      <Route path="/createaccount" element={<CreateAccount/>}/>
      <Route path='/index' element={<App/>}/>
  </Routes>
</BrowserRouter>,
  document.getElementById('root')
);

reportWebVitals();
