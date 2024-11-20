import axios from 'axios';
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { request, setAuthToken } from '../axios_helper';
import CoachValidation from '../validation/CoachValidation';
import '../style/Coach.css';
import { FaCalendar,FaPhone,FaCheck,FaVenusMars,FaUser,FaLock,FaExclamationTriangle } from 'react-icons/fa';
import Alert from 'react-bootstrap/Alert';


export default function AddCoach() {

    let navigate = useNavigate()

    
    const [message, setMessage] = useState("");
    const [errors, setErrors] = useState({});
    const [showError, setShowError] = useState(false);
    const [showSuccess, setShowSuccess] = useState(false);

    const [coach, setCoach] = useState({
        firstname: '',
        lastname: '',
        birthday:'',
        gender: '',
        phone: '',
        username: '',
        password: ''
    });

    const { firstname, lastname, birthday, gender, phone, username, password } = coach;

    const onInputChange = (e) => {
        setCoach({ ...coach, [e.target.name]: e.target.value });
    };

    const onSubmit=async(e)=>{
        e.preventDefault();
        setErrors(null);
        setShowError(false);
        setShowSuccess(false);
        // console.log(coach);
        const validationErrors = CoachValidation(coach);
        setErrors(validationErrors);
        console.log(validationErrors);

        const errorsArray = Object.values(validationErrors);
        const hasErrors = errorsArray.some(error => error !== undefined && error !== null);

        
        if (!hasErrors) {
            try {
                // Slanje zahteva ka serveru samo ako nema grešaka
                const response = await request('POST','http://localhost:8080/add-coach', coach);
                // window.alert("New coach has been created succsessfully");
                setShowSuccess(true);
                setMessage("Coach has been created successfully");
                // navigate("/coaches");
            } catch (error) {
                if (error.response) {
                    console.log(error.response.data)
                    setMessage(error.response.data);
                    // window.alert(error.response.data.error );
                    // window.alert("You must be the owner to add Coach");
                    setShowError(true);
                    // setMessage("Cannot create new coach");

                } else {
                    setMessage("An unexpected error occurred.");
                    window.alert("An unexpected error occurred.");
                    setShowError(true);

                }
            }
        } else {
            console.log("Validation returned errors. Server request isn't sent");
        }
        

    }

    
    return (
        <div className="coach-container">

            <div className="coach-content-container">
                
                 {/* <div className="pozadina"> */}
                    <h2 className="text-center m-4">Add Coach</h2>

                    <form className="row g-3" onSubmit={(e) => onSubmit(e)}>
                        
                    <div className="col input-group">
                        <label htmlFor="firstname" className="input-group-text">Firstname</label>
                        <input type="text" className="form-control" placeholder="Enter firstname" name="firstname" value={firstname} onChange={(e) => onInputChange(e)} />
                        {errors.firstname && <p className="text-danger">{errors.firstname}</p>}
                    </div>

                    <div className="col input-group">
                        <label htmlFor="lastname" className="input-group-text">Lastname</label>
                        <input type="text" className="form-control" placeholder="Enter lastname" name="lastname" value={lastname} onChange={(e) => onInputChange(e)} />
                        {errors.lastname && <p className="text-danger">{errors.lastname}</p>}
                    </div>

                    <div className="input-group">
                        <label htmlFor="birthday" className="input-group-text"><FaCalendar/></label>
                        <input type="date" className="form-control" placeholder="Enter birthday" name="birthday" value={birthday} onChange={(e) => onInputChange(e)} />
                        {errors.birthday && <p className="text-danger">{errors.birthday}</p>}
                    </div>

                    <div className="input-group">
                        <label htmlFor="gender" className="input-group-text"><FaVenusMars/></label>
                        <select className="form-control" name="gender" value={gender} onChange={(e) => onInputChange(e)}>
                            <option value="">Select gender</option>
                            <option value="MALE">MALE</option>
                            <option value="FEMALE">FEMALE</option>
                        </select>
                        {errors.gender && <p className="text-danger">{errors.gender}</p>}
                    </div>

                    <div className="input-group">
                        <label htmlFor="phone" className="input-group-text"><FaPhone/></label>
                        <input type="text" className="form-control" placeholder="Enter phone number" name="phone" value={phone} onChange={(e) => onInputChange(e)} />
                        {errors.phone && <p className="text-danger ">{errors.phone}</p>}
                    </div>

                    <div className="input-group">
                        <label htmlFor="username" className="input-group-text"><FaUser/></label>
                        <input type="text" className="form-control" placeholder="Enter username" name="username" value={username} onChange={(e) => onInputChange(e)} />
                        {errors.username && <p className="text-danger">{errors.username}</p>}
                    </div>

                    <div className="mb-3 input-group">
                        <label htmlFor="password" className="input-group-text"><FaLock/></label>
                        <input type="password" className="form-control" placeholder="Enter password" name="password" value={password} onChange={(e) => onInputChange(e)} />
                        {errors.password && <p className="text-danger">{errors.password}</p>}
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

                    <button type="submit" className="col btn btn-outline-secondary">Submit</button>
                    <Link className="col btn btn-outline-danger mx-2" to="/">Cancel</Link>
                    {/* {error && <p className="text-danger">{errorForm}</p>} */}
                    </form>
                </div>
            </div>
    );
}
