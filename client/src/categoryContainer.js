import React from 'react';

function CategoryContainer({category, jobs}) {
  const categoryJobs = jobs.filter(job => job.jobStatus === category);
  return(
    <>
    <h2>{category} jobs</h2>
    {categoryJobs.length > 0 && categoryJobs.map(job => {
      return (
        <>
        <h3>job container component</h3>
        <p>{job.title}</p>
        </>
      )
    })}
    {categoryJobs.length === 0 && <h3>No {category} jobs</h3>}
    </>
  )
}

export default CategoryContainer;