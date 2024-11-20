import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { Link, useParams } from 'react-router-dom';
import { FaInfo, FaEdit, FaTrash,FaSearch } from 'react-icons/fa';

const ListTrainingSessions = () => {

  const [tSessions,setTSessions] = useState([])
  const [searchTerm, setSearchTerm] = useState('');
  const [filteredTSessions, setFilteredTSessions] = useState([]);
  
  const {id}= useParams()

  useEffect(()=>{
    loadTSessions();
  },[])

  const loadTSessions=async()=>{
    const result =await axios.get("http://localhost:8080/sessions")
    console.log(result.data);
    setTSessions(result.data);
    setFilteredTSessions(result.data);
  };

  const deleteTSession=async (id) => {
    const confirmDelete = window.confirm("Are you sure you want to delete?");
    if(confirmDelete){
      await axios.delete(`http://localhost:8080/session/${id}`)
      loadTSessions()
    }
  }

  const handleSearchChange = (event) => {
    setSearchTerm(event.target.value);
    
    if (searchTerm === ''|| searchTerm.length < 2) {
      setFilteredTSessions(tSessions); // Vraćanje svih članova kada se polje za pretragu isprazni
    } else {
        const filtered = tSessions.filter(tSession =>
          tSession.coachName.toLowerCase().includes(searchTerm.toLowerCase()) ||
          tSession.memberName.toLowerCase().includes(searchTerm.toLowerCase())
        );    
        setFilteredTSessions(filtered);
        console.log(filtered);
        }
    
  };

  return (
    <div className = "session-container">
      <div className = "sessions-content-container">
        <h2 className="tet-center">List of Training Sessions</h2>
        <div className="input-group mb-3">
            <label className="input-group-text py-2"><FaSearch/></label>
            <input type="text" placeholder="search coach/member" value={searchTerm} onChange={handleSearchChange} />
        </div>
        <table className="table table-bordered table-striped">
            <thead>
                <th>Session ID</th>
                <th>Date</th>
                <th>Time</th>
                <th>Coach</th>
                <th>Member</th>
                <th>Action</th>
            </thead>
            <tbody>
                {
                    filteredTSessions.map(tSession =><tr key={tSession.id}>
                      <td>{tSession.id}</td>
                      <td>{new Date(tSession.date).toLocaleDateString()}</td>
                      <td>{tSession.time}</td>
                      <td>{tSession.coachName}</td>
                      <td>{tSession.memberName}</td>
                      <td>
                        <Link className="btn btn-secondary mx-2" to={`/view-session/${tSession.id}`}><FaInfo/></Link>
                        <Link className="btn btn-dark mx-2" to={`/edit-session/${tSession.id}`} ><FaEdit/></Link>
                        <button className="btn btn-danger mx-2" onClick={()=>deleteTSession(tSession.id)}><FaTrash/></button>
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

export default ListTrainingSessions