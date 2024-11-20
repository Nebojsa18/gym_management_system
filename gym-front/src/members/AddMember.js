import axios from 'axios';
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import MemberValidation from '../validation/MemberValidation';
import '../style/Member.css';
import { FaCalendar,FaPhone,FaEnvelope,FaVenusMars,FaCheck,FaExclamationTriangle } from 'react-icons/fa';
import Alert from 'react-bootstrap/Alert';

export default function AddMember() {


    let navigate = useNavigate()

    const [member, setMember] = useState({
        firstname: "",
        lastname: "",
        birthday: "",
        gender: "",
        phone: "",
        email: "",
        
    });

    const [message, setMessage] = useState("");
    const [errors, setErrors] = useState({});
    const [showError, setShowError] = useState(false);
    const [showSuccess, setShowSuccess] = useState(false);

    const { firstname, lastname, email,birthday, gender, phone} = member;

    const onInputChange = (e) => {
        setMember({ ...member, [e.target.name]: e.target.value });
    };

    const onSubmit=async(e)=>{
        e.preventDefault();
        const validationErrors = MemberValidation(member);
        setErrors(validationErrors);
        console.log(validationErrors);
        setShowError(false);
        setShowSuccess(false);

        const errorsArray = Object.values(validationErrors);
        const hasErrors = errorsArray.some(error => error !== undefined && error !== null);

        
        if (!hasErrors) {
            try {
                // Slanje zahteva ka serveru samo ako nema grešaka
                const response = await axios.post("http://localhost:8080/add-member", member);
                // navigate("/members");
                setShowSuccess(true);
                setMessage("Member has been created successfully");

            } catch (error) {
                if (error.response) {
                    console.log(error.response.data)
                    
                    setShowError(true);
                    setMessage("Cannot delete member!");
                    // window.alert(error.response.data.error );
                    // window.alert("You must be the owner to add Coach");
                } else {
                    setMessage("Cannot create new member.");
                    setShowError(true)
                    // window.alert("An unexpected error occurred.");
                }
            }
        } else {
            console.log("Validation returned errors. Server request isn't sent");
        }
        // try {
        //     const response = await 
        //     navigate("/members");
        // } catch (error) {
        //     if (error.response) {
        //         setError(error.response.data);
        //         window.alert(error.response.data);
        //     } else {
        //         setError("An unexpected error occurred.");
        //         window.alert("An unexpected error occurred.");
        //     }
        // }
        

    }

    

    return (
        <div className="member-container">
            <div className="member-content-container">
                    <h2 className="text-center m-4">Add member</h2>

                    <form className="row g-3" onSubmit={(e) => onSubmit(e)}>
                    <div className="col mb-6 input-group">
                        <label htmlFor="firstname" className="input-group-text">Firstname</label>
                        <input type="text" className="form-control" placeholder="Enter firstname" name="firstname" value={firstname} onChange={(e) => onInputChange(e)} />
                        {errors.firstname && <p className="text-danger">{errors.firstname}</p>}
                    </div>

                    <div className="col md-6 input-group">
                        <label htmlFor="lastname" className="input-group-text">Lastname</label>
                        <input type="text" className="form-control" placeholder="Enter lastname" name="lastname" value={lastname} onChange={(e) => onInputChange(e)} />
                        {errors.lastname && <p className="text-danger">{errors.lastname}</p>}
                    </div>

                    <div className="md-6 input-group">
                        <label htmlFor="birthday" className="input-group-text"><FaCalendar/></label>
                        <input type="date" className="form-control" placeholder="Enter birthday" name="birthday" value={birthday} onChange={(e) => onInputChange(e)} />
                        {errors.birthday && <p className="text-danger">{errors.birthday}</p>}
                    </div>

                    <div className="md-6 input-group">
                        <label htmlFor="gender" className="input-group-text"><FaVenusMars/></label>
                        <select className="form-control" name="gender" value={gender} onChange={(e) => onInputChange(e)}>
                            <option value="">Select gender</option>
                            <option value="MALE">MALE</option>
                            <option value="FEMALE">FEMALE</option>
                        </select>
                        {errors.gender && <p className="text-danger">{errors.gender}</p>}
                    </div>

                    <div className="md-6 input-group">
                        <label htmlFor="phone" className="input-group-text"><FaPhone/></label>
                        <input type="text" className="form-control" placeholder="Enter phone number" name="phone" value={phone} onChange={(e) => onInputChange(e)} />
                        {errors.phone && <p className="text-danger">{errors.phone}</p>}
                    </div>

                    <div className="mb-3 input-group">
                        <label htmlFor="email" className="input-group-text"><FaEnvelope/></label>
                        <input type="email" className="form-control" placeholder="Enter email" name="email" value={email} onChange={(e) => onInputChange(e)} />
                        {errors.email && <p className="text-danger">{errors.email}</p>}
                    </div>

                    {showError && (
                        <Alert variant="danger" onClose={() => setShowError(false)} dismissible>
                            <FaExclamationTriangle/> <p3>{message}</p3>
                        </Alert>
                    )}

                    {showSuccess && (
                        <Alert variant="success" onClose={() => setShowSuccess(false)} dismissible>
                            <FaCheck/> <p3>{message}</p3>
                        </Alert>
                    )}

                    <button type="submit" className="col btn btn-outline-secondary">Submit</button>
                    <Link className="col btn btn-outline-danger mx-2" to="/">Cancel</Link>
                    </form>
                </div>
            </div>

    );
}