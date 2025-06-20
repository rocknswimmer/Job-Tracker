import React from 'react';
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import JobContainer from './jobContainer';

function App() {

  return (
    <>
    <header>
      <h1>Job Tracker</h1>
    </header>
    <Router>
      <Routes>
        <Route path="/" element={<JobContainer />}></Route>
      </Routes>
    </Router>
    </>
  )
}

export default App;