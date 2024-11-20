import axios from 'axios';
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { request, setAuthToken } from '../axios_helper';
// import CoachValidation from '../validation/CoachValidation';
import '../style/MembershipType.css';
import { FaComment,FaDollarSign,FaEnvelope,FaVenusMars,FaUser,FaLock } from 'react-icons/fa';


export default function EditMembershipType() {

    let navigate = useNavigate()
    const {id} =useParams()
    
    const [error, setError] = useState("");
    const [errors, setErrors] = useState({});

    

    const [membershipType, setMembershipType] = useState({
        name: '',
        termCount: '',
        price:'',
        description: '',
    });

    const { name,termCount,price,description  } = membershipType;

    const onInputChange = (e) => {
        setMembershipType({ ...membershipType, [e.target.name]: e.target.value });
    };

    const onSubmit=async(e)=>{
        e.preventDefault();
        setErrors(null);
        // console.log(coach);
        // const validationErrors = CoachValidation(coach);
        // setErrors(validationErrors);
        // console.log(validationErrors);

        // const errorsArray = Object.values(validationErrors);
        // const hasErrors = errorsArray.some(error => error !== undefined && error !== null);

        
        // if (!hasErrors) {
            try {
                // Slanje zahteva ka serveru samo ako nema grešaka
                const response = await request('POST',`http://localhost:8080/membership-type/${id}`, membershipType);
                navigate("/home");
            } catch (error) {
                if (error.response) {
                    console.log(error.response.data)
                    setError(error.response.data);
                    window.alert(error.response.data.error );
                    window.alert("You must be the owner to add Coach");
                } else {
                    setError("An unexpected error occurred.");
                    window.alert("An unexpected error occurred.");
                }
            }
        // } else {
        //     console.log("Validation returned errors. Server request isn't sent");
        // }
        

    }

    
    return (
        <div className="membership-type-container">

            <div className="membership-type-content-container">
                
                 {/* <div className="pozadina"> */}
                    <h2 className="text-center m-4">Update membership type</h2>

                    <form className="row g-3" onSubmit={(e) => onSubmit(e)}>
                        
                    <div className="input-group">
                        <label htmlFor="firstname" className="input-group-text">Name</label>
                        <input type="text" className="form-control" placeholder="Enter name" name="name" value={name} onChange={(e) => onInputChange(e)} />
                        {errors.firstname && <p className="text-danger">{errors.firstname}</p>}
                    </div>

                    <div className="input-group">
                        <label htmlFor="session-count" className="input-group-text">Term count</label>
                        <input type="text" className="form-control" placeholder="Enter number of sessions" name="numOfSessions" value={termCount} onChange={(e) => onInputChange(e)} />
                        {errors.lastname && <p className="text-danger">{errors.lastname}</p>}
                    </div>

                    <div className="input-group">
                        <label htmlFor="price" className="input-group-text"></label>
                        <input type="text" className="form-control" placeholder="Enter price" name="price" value={termCount} onChange={(e) => onInputChange(e)} />
                        {errors.lastname && <p className="text-danger">{errors.lastname}</p>}
                    </div>

                    

                    <div className="input-group">
                        <label htmlFor="description" className="input-group-text"><FaComment/></label>
                        <textarea type="text" className="form-control" placeholder="Enter description" name="description" value={description} onChange={(e) => onInputChange(e)} />
                        {errors.phone && <p className="text-danger ">{errors.phone}</p>}
                    </div>

                    

                    <button type="submit" className="col btn btn-outline-secondary">Submit</button>
                    <Link className="col btn btn-outline-danger mx-2" to="/">Cancel</Link>
                    {/* {error && <p className="text-danger">{errorForm}</p>} */}
                    </form>
                </div>
            </div>
    );
}
