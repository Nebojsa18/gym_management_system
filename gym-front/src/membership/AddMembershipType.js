import axios from 'axios';
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { request, setAuthToken } from '../axios_helper';
// import CoachValidation from '../validation/CoachValidation';
import '../style/MembershipType.css';
import { FaComment,FaExclamationTriangle,FaCheck} from 'react-icons/fa';
import Alert from 'react-bootstrap/Alert';

export default function AddMembershipType() {

    let navigate = useNavigate()

    
    

    const [message, setMessage] = useState("");
    const [showError, setShowError] = useState(false);
    const [showSuccess, setShowSuccess] = useState(false);
    

    const [membershipType, setMembershipType] = useState({
        name: '',
        numOfSessions: '',
        price:'',
        description: '',
    });

    const { name,numOfSessions,price,description  } = membershipType;

    const onInputChange = (e) => {
        setMembershipType({ ...membershipType, [e.target.name]: e.target.value });
    };

    const onSubmit=async(e)=>{
        e.preventDefault();
        
        setShowError(false);
        setShowSuccess(false); 
        
        try {
            // Slanje zahteva ka serveru samo ako nema grešaka
            const response = await request('POST','http://localhost:8080/add-membership-type', membershipType);
            console.log(membershipType);
            setShowSuccess(true);
            setMessage("Membership type has been created successfully");
            // navigate("/home");
        } catch (error) {
            if (error.response) {
                // console.log(error.response.data)
                    
                setShowError(true);
                setMessage("Cannot create new membership type");
            } else {
                setMessage("An unexpected error occurred.");
                setShowError(true);            }
        }
        

    }

    
    return (
        <div className="membership-type-container">

            <div className="membership-type-content-container">
                
                 {/* <div className="pozadina"> */}
                    <h2 className="text-center m-4">Add membership type</h2>

                    <form className="row g-3" onSubmit={(e) => onSubmit(e)}>
                        
                    <div className="input-group">
                        <label htmlFor="name" className="input-group-text">Name</label>
                        <input type="text" className="form-control" placeholder="Enter name" name="name" value={name} onChange={(e) => onInputChange(e)} />
                        {/* {errors.firstname && <p className="text-danger">{errors.firstname}</p>} */}
                    </div>

                    <div className="input-group">
                        <label htmlFor="session-count" className="input-group-text">Sessions:</label>
                        <input type="text" className="form-control" placeholder="Enter number of sessions" name="numOfSessions" value={numOfSessions} onChange={(e) => onInputChange(e)} />
                        {/* {errors.lastname && <p className="text-danger">{errors.lastname}</p>} */}
                    </div>

                    <div className="input-group">
                        <label htmlFor="price" className="input-group-text">Price</label>
                        <input type="text" className="form-control" placeholder="Enter price" name="price" value={price} onChange={(e) => onInputChange(e)} />
                        {/* {errors.lastname && <p className="text-danger">{errors.lastname}</p>} */}
                    </div>

                    

                    <div className="input-group">
                        <label htmlFor="description" className="input-group-text"><FaComment/></label>
                        <textarea type="text" className="form-control" placeholder="Enter description" name="description" value={description} onChange={(e) => onInputChange(e)} />
                        {/* {errors.phone && <p className="text-danger ">{errors.phone}</p>} */}
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
