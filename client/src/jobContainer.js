import React, {useState, useEffect} from 'react';
import CategoryContainer from './categoryContainer';


function JobContainer() {
  const [jobs, setJobs] = useState([]);
  const url = "http://localhost:8080/api/jobs";

  useEffect(() => {
    getJobs();
  },[])

  const getJobs = () => {
    fetch(url)
    .then(response => {
      if(response.status === 200){
        return response.json();
      } else {
        return Promise.reject(`Unexpected Status Code: ${response.status}`);
      }
    })
    .then(data => setJobs(data))
    .catch(console.log())
  }

  return (
    <>
      <h2>job container goes here</h2>
      <div class="d-flex flex-row">
        <CategoryContainer category="APPLIED" jobs={jobs} />
        <CategoryContainer category="REJECTED" jobs={jobs} />
        <CategoryContainer category="PHONE" jobs={jobs} />
        <CategoryContainer category="TECH" jobs={jobs} />
        <CategoryContainer category="BEHAVIORAL" jobs={jobs} />
        <CategoryContainer category="FINAL" jobs={jobs} />
        <CategoryContainer category="OFFER" jobs={jobs} />
      </div>
    </>
  );
}

export default JobContainer;