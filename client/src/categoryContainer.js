import React from 'react';

function CategoryContainer({category, jobs}) {
  const categoryJobs = jobs.filter(job => job.jobStatus === category);
  return(
    <div class="d-flex flex-column">
    <h2>{category} jobs</h2>
    {categoryJobs.length > 0 &&
    <div class="d-flex flex-column">
      {categoryJobs.map(job => {
      return (
        //accodion here

        <>
        <h3>job component</h3>
        <p>{job.title}</p>
        </>
      )
    })}
    </div>}
    {categoryJobs.length === 0 && <h3>No {category} jobs</h3>}
    </ div>
  )
}

export default CategoryContainer;
