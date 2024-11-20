import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { Link, useParams } from 'react-router-dom';
import { request } from '../axios_helper';
import { FaInfo, FaEdit, FaTrash, FaSearch } from 'react-icons/fa';
import '../style/Coach.css';

const ListCoaches = () => {

  const [coaches,setCoaches] = useState([])
  const [searchTerm, setSearchTerm] = useState('');
  const [filteredCoaches, setFilteredCoaches] = useState([]);
  
  const {id}= useParams()

  useEffect(()=>{
    loadCoaches();
  },[])

  const loadCoaches=async()=>{
    // const result =await axios.get("http://localhost:8080/coaches")
    try {
      const result = await request("GET","http://localhost:8080/coaches" )
      console.log(result.data);
      setCoaches(result.data);
      setFilteredCoaches(result.data);
    } catch (error) {
      console.error("Error loading coaches:", error);
      alert(error.response.data.error + " for the role Employee")
    }
    
  };

  const handleSearchChange = (event) => {
    setSearchTerm(event.target.value);
    
    if (searchTerm === '' || searchTerm.length < 2) {
      setFilteredCoaches(coaches); // Vraćanje svih članova kada se polje za pretragu isprazni
    } else {
        const filtered = coaches.filter(coach =>
          coach.firstname.toLowerCase().includes(searchTerm.toLowerCase()) ||
          coach.lastname.toLowerCase().includes(searchTerm.toLowerCase())
        );    
        setFilteredCoaches(filtered);
        console.log(filtered);
        }
    
  };

  const deleteUser=async (coach) => {
    // await axios.delete(`http://localhost:8080/coach/${id}`)
    const confirmDelete = window.confirm("Are you sure you want to delete "+ coach.firstname + " " + coach.lastname + "?");
    if(confirmDelete){
      try {
        const result = await request("DELETE",`http://localhost:8080/coach/${coach.id}`)
        console.log(result.data);
      } catch (error) {
        console.error("Error deleting coach: ", error);
        alert(error.response.data.error + " for the role Employee")
      }
      loadCoaches()
    }
    
  }

  return (
    <div className = "coach-container">
      <div className="coaches-content-container">
        <h2 className="tet-center">List of Coaches</h2>
        <div className="input-group mb-3">
            <label className="input-group-text py-2"><FaSearch/></label>
            <input type="text" placeholder="search coach" value={searchTerm} onChange={handleSearchChange} />
        </div>
        <table className="table table-bordered table-striped">
            <thead>
                {/* <th>Coach ID</th> */}
                <th>First Name</th>
                <th>Last Name</th>
                <th>Birthday</th>
                <th>Gender</th>
                <th>Phone</th>
                <th>Action</th>
            </thead>
            <tbody>
                {
                    filteredCoaches.map(coach =><tr key={coach.id}>
                      {/* <td>{coach.id}</td> */}
                      <td>{coach.firstname}</td>
                      <td>{coach.lastname}</td>
                      {/* <td>{coach.birthday}</td> */}
                      <td>{new Date(coach.birthday).toLocaleDateString()}</td>
                      <td>{coach.gender}</td>
                      <td>{coach.phone}</td>
                      <td>
                        <Link className="btn btn-secondary mx-2" to={`/view-coach/${coach.id}`}>
                           <FaInfo />
                        </Link>
                        <Link className="btn btn-dark mx-2"
                         to ={`/edit-coach/${coach.id}`}>
                          <FaEdit /> 
                        </Link>
                        <Link className="btn btn-danger mx-2"
                        onClick={()=>deleteUser(coach)}
                        ><FaTrash />
                        </Link>
                      </td>
                    </tr>
                    )
                }
            </tbody>
        </table>
        </div>
    </div>
  )
}

export default ListCoaches