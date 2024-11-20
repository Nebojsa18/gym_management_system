import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { Link, useParams } from 'react-router-dom';
import { FaInfo, FaEdit, FaTrash,FaSearch } from 'react-icons/fa';
import '../style/Member.css';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';



const ListMembers = () => {

  const [members,setMembers] = useState([])
  const [searchTerm, setSearchTerm] = useState('');
  const [filteredMembers, setFilteredMembers] = useState([]);
  const [modalShow, setModalShow] = React.useState(false);

  const [selectedMember, setSelectedMember] = useState(null);

  const {id}= useParams()

  useEffect(()=>{
    loadMembers();
  },[])

  const loadMembers=async()=>{
    const result =await axios.get("http://localhost:8080/members")
    console.log(result.data);
    setMembers(result.data);
    setFilteredMembers(result.data);
  };

  const deleteMember=async () => {

    if(selectedMember){
      await axios.delete(`http://localhost:8080/member/${selectedMember.id}`)
      loadMembers()
      setModalShow(false);
      setSelectedMember(null);
    }
  }

  const handleSearchChange = (event) => {
    setSearchTerm(event.target.value);
    
    if (searchTerm === '' || searchTerm.length < 2) {
      setFilteredMembers(members); // Vraćanje svih članova kada se polje za pretragu isprazni
    } else {
        const filtered = members.filter(member =>
          member.firstname.toLowerCase().includes(searchTerm.toLowerCase()) ||
          member.lastname.toLowerCase().includes(searchTerm.toLowerCase())
        );    
        setFilteredMembers(filtered);
        console.log(filtered);
        }
    
  };

  const handleDelete = (member) => {
    setSelectedMember(member);
    setModalShow(true);
    console.log(selectedMember)
  };

  


  return (
    <div className = "member-container">
      <div className="members-content-container">
        <h2 className="tet-center">List of Members</h2>
        <div className="input-group mb-3">
            <label className="input-group-text py-2"><FaSearch/></label>
            <input type="text" placeholder="search member" value={searchTerm} onChange={handleSearchChange} />
        </div>
        <table className="table table-bordered table-striped">
            <thead>
                {/* <th>Member ID</th> */}
                <th>First Name</th>
                <th>Last Name</th>
                <th>E-mail</th>
                <th>Birthday</th>
                <th>Gender</th>
                <th>Phone</th>
                <th>Action</th>
            </thead>
            <tbody>
                {
                    filteredMembers.map(member =><tr key={member.id}>
                    
                      {/* <td>{member.id}</td> */}
                      <td>{member.firstname}</td>
                      <td>{member.lastname}</td>
                      <td>{member.email}</td>
                      {/* <td>{member.birthday}</td> */}
                      <td>{new Date(member.birthday).toLocaleDateString()}</td>
                      <td>{member.gender}</td>
                      <td>{member.phone}</td>
                      <td>
                        <Link className="btn btn-secondary mx-2" to={`/view-member/${member.id}`}><FaInfo/></Link>
                        <Link className="btn btn-dark mx-2"
                         to ={`/edit-member/${member.id}`}><FaEdit/></Link>
                        <button className="btn btn-danger mx-2"
                        // onClick={()=>deleteMember(member)}
                        onClick={()=>handleDelete(member)}
                        ><FaTrash/></button>
                      </td>
                    </tr>
                    )
                }
            </tbody>
        </table>
      </div>
      <ModalDelete
        show={modalShow}
        onHide={() => setModalShow(false)}
        onConfirm={deleteMember}
      />
    </div>
  )
  
}

function ModalDelete(props) {
  return (
    <Modal
      {...props}
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered
    >
      <Modal.Header closeButton>
        <Modal.Title id="contained-modal-title-vcenter">
          Confirm
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <h4>Are you sure you want to delete this member?</h4>

      </Modal.Body>
      <Modal.Footer>
        <Button variant="success" onClick={props.onConfirm}>Yes</Button>
        <Button variant="danger" onClick={props.onHide}>No</Button>
      </Modal.Footer>
    </Modal>
  );
}

export default ListMembers