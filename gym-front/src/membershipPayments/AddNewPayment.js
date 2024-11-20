import React, { useState, useEffect} from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import { FaCheck,FaExclamationTriangle} from 'react-icons/fa';
import {getLoggedCoach} from '../axios_helper';
import '../style/MembershipPayment.css';
import Alert from 'react-bootstrap/Alert';


export default function  MembershipPaymentForm(){
//   State za članove i trenere
let navigate = useNavigate()
const [error, setError] = useState("");
const [coaches,setCoaches]=useState([]);
const [members, setMembers] = useState([]);
const [membershipTypes,setMembershipTypes] = useState([]);

const [coach, setCoach] = useState(getLoggedCoach());
const [member, setMember] = useState({});
const [membershipType, setMembershipType] = useState([]);
const [searchTerm, setSearchTerm] = useState('');
const [filteredMembers, setFilteredMembers] = useState([]);
const [errorItem, setErrorItem] = useState([]);

const [message, setMessage] = useState("");
const [showError, setShowError] = useState(false);
const [showSuccess, setShowSuccess] = useState(false);



useEffect(()=>{
    async function fetchMembershipTypes(){
        try {
            const response = await axios.get("http://localhost:8080/membership-types");
            setMembershipTypes(response.data);
            console.log(response.data);
        } catch (error) {
            console.error("error loading membershipTypes: ",error);
        }
    }
    fetchMembershipTypes();
},[]);

  useEffect(() => {
    // Učitavanje liste trenera (ili objekata)
    async function fetchCoaches() {
        try {
            const response = await axios.get("http://localhost:8080/coaches");
            setCoaches(response.data);
            console.log(response.data);
        } catch (error) {
            console.error("Error loading coaches:", error);
        }
    }
    fetchCoaches();
}, []);

useEffect(() => {
    // Učitavanje liste clanova (ili objekata)
    async function fetchMembers() {
        try {
            const response = await axios.get("http://localhost:8080/members");
            setMembers(response.data);
            setFilteredMembers(response.data);
            console.log(response.data);
        } catch (error) {
            console.error("Error loading members", error);
        }
    }
    fetchMembers();
}, []);

const handleCoachChange=(event)=>{
    const selectedId = parseInt(event.target.value);
    const coach = coaches.find(ex=> ex.id===selectedId);
    console.log("TRENER U PROMENI")
    console.log(coach);
    setCoach(coach);
  }

  const handleMembershipTypeChange=(event)=>{
    const selectedId = parseInt(event.target.value);
    const membershipType = membershipTypes.find(ex=> ex.id===selectedId);
    console.log("MembershipType U PROMENI")
    console.log(membershipType);
    setMembershipType(membershipType);
  }

  const handleSearchChange = (event) => {
    setSearchTerm(event.target.value);
    
    if (searchTerm === ''|| searchTerm.length < 2) {
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

  const handleMemberSelect = (memberId) => {
    const selectedMember = members.find(member => member.id === memberId);
    setMember(selectedMember);
    console.log("Izabran član:", member);
  };


  const onSubmit=async(e)=>{
    e.preventDefault();
    setShowError(false);
    setShowSuccess(false);
    if (!member.id) {
        setErrorItem("Please select a member.");
        return;
    }
    try {

        const newPayment = {
            member,
            coach,
            membershipType,
            paymentDate: new Date().toISOString().split('T')[0],
            validUntil: new Date(new Date().setMonth(new Date().getMonth() + 1)).toISOString().split('T')[0],
            remainingSessions: membershipType.numOfSessions,
            usedSessions: 0
        };

        const response = await axios.post("http://localhost:8080/add-payment", newPayment);
        // navigate("/home");
        console.log("ZA CUVANJE");
        console.log(newPayment);
        console.log(membershipType.numOfSessions);
        setShowSuccess(true);
        setMessage("Membership payment has been created successfully");
    } catch (error) {
        if (error.response) {
            setMessage(error.response.data);
            setShowError(true);
        } else {
            setMessage("An unexpected error occurred.");
            setShowError(true);
        }
    }
    

}

  return (
    <div className="payment-container">
        <div className="payment-content-container">
            <h2 className="text-center mb-2 pb-4">Add Membership Payment</h2>
            <form onSubmit={(e) => onSubmit(e)}>
                <div className="input-group mb-3">
                        <label className="input-group-text">Coach:</label>
                        <select className="form-control" disabled value={coach.id} onChange={handleCoachChange} required>
                        <option value="">Select Coach</option>
                        {coaches.map((coach) => (
                            <option key={coach.id} value={coach.id}>{coach.firstname+ " " + coach.lastname}</option>
                        ))}
                        </select>
                </div>
                    <div className="input-group mb-3">
                        <label className="input-group-text">Member:</label>
                        <select className="form-control" disabled value={member.id ? member.id : ''} onChange={handleMemberSelect}>
                            <option value="">{member.id ? `${member.firstname} ${member.lastname}` : 'Select Member'}</option>
                            {filteredMembers.map(member => (
                                <option key={member.id} value={member.id}>{`${member.firstname} ${member.lastname}`}</option>
                            ))}
                        </select>
                    </div>
                    <div className="input-group mb-3">
                        <label className="input-group-text">Search:</label>
                        <input type="text" placeholder="search members" value={searchTerm} onChange={handleSearchChange} />
                    </div>
                    <div className="mb-3 table-container">
                            {/* <label>Member:</label> */}
                        <table className="table table-bordered table-striped">
                        <thead>
                            <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            {filteredMembers.map(member => (
                            <tr key={member.id}>
                                <td>{member.id}</td>
                                <td>{member.firstname} {member.lastname}</td>
                                <td><button className="btn btn-outline-secondary" onClick={() => handleMemberSelect(member.id)}>Select</button></td>
                            </tr>
                            ))}
                        </tbody>
                        </table>
                    </div>
                    <div className="input-group mb-3">
                        <label className="input-group-text">Type:</label>
                        <select className="form-control" value={membershipType.id} onChange={handleMembershipTypeChange} required>
                        {/* <option value="">Select MembershipType</option> */}
                        {membershipType.id ? null : <option value="">Select MembershipType</option>}
                        {membershipTypes.map((type) => (
                            <option key={type.id} value={type.id}>{type.name + " - Price: "+ type.price}</option>
                        ))}
                        </select>
                    </div>
                    {showError && (
                        <Alert variant="danger" onClose={() => setShowError(false)} dismissible>
                            {/* <Alert.Heading>Cannot create new coach!</Alert.Heading> */}
                            <FaExclamationTriangle/> <p3>{message}</p3>
                        </Alert>
                    )}

                    {showSuccess && (
                        <Alert variant="success" onClose={() => setShowSuccess(false)} dismissible>
                            {/* <Alert.Heading>Cannot create new coach!</Alert.Heading> */}
                            <FaCheck/> <p3>{message}</p3>
                        </Alert>
                    )}
                    {/* {errorItem && <p className="text-danger">{errorItem}</p>} */}
                    <button type="submit" className="btn btn-outline-secondary">Submit</button>
            </form>
        </div>
    </div>
  );
};


