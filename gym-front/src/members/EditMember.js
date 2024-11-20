import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { FaCalendar,FaPhone,FaEnvelope,FaCheck,FaVenusMars,FaUser,FaKey,FaExclamationTriangle } from 'react-icons/fa';
import MemberValidation from '../validation/MemberValidation';
import Alert from 'react-bootstrap/Alert';


export default function EditMember() {

    let navigate = useNavigate()

    const {id} =useParams()

    const [message, setMessage] = useState("");
    const [errors, setErrors] = useState({});
    const [showError, setShowError] = useState(false);
    const [showSuccess, setShowSuccess] = useState(false);

    const [member, setMember] = useState({
        firstname: "",
        lastname: "",
        email:"",
        birthday: "",
        gender: "",
        phone: "",
        
    });

    const { firstname, lastname, email ,birthday, gender, phone } = member;

    const onInputChange = (e) => {
        setMember({ ...member, [e.target.name]: e.target.value });
    };

    

    const loadMember = async ()=>{
        const result=await axios.get(`http://localhost:8080/member/${id}`)
        const loadedMember = result.data;
        // Konvertujte datum u format YYYY-MM-DD
        if (loadedMember.birthday) {
            loadedMember.birthday = loadedMember.birthday.substring(0, 10);
        }
        setMember(loadedMember);
        setMember(result.data)
    }

    useEffect(()=>{
        loadMember()
    },[])

    const onSubmit=async(e)=>{
        e.preventDefault();

        e.preventDefault();
        const validationErrors = MemberValidation(member);
        setErrors(validationErrors);
        // console.log(validationErrors);

        setShowError(false);
        setShowSuccess(false);

        const errorsArray = Object.values(validationErrors);
        const hasErrors = errorsArray.some(error => error !== undefined && error !== null);
        if (!hasErrors) {
            try{
                await axios.put(`http://localhost:8080/member/${id}`,member)
                // navigate("/members")
                setShowSuccess(true);
                setMessage("Member has been updated succesfully!")
            }catch (error) {
                if (error.response) {
                    console.log(error.response.data)
                    setShowError(true);
                    setMessage("Cannot update member");
                    // setError(error.response.data);
                    // window.alert(error.response.data.error );
                    // window.alert("You must be the owner to add Coach");
                } else {
                    // setError("An unexpected error occurred.");
                    setShowError(true);
                    setMessage("An unexpected error occurred.");

                }
            }
        } else {
            console.log("Validation returned errors. Server request isn't sent");
        }

    }

    return (
        <div className="member-container">
            <div className="member-content-container">
                
                    <h2 className="text-center m-4">Edit Member</h2>

                    <form className="row g-3" onSubmit={(e) => onSubmit(e)}>

                    <div className="col mb-6 input-group">
                        <label htmlFor="firstname" className="input-group-text">Firstname</label>
                        <input type="text" className="form-control" placeholder="Enter firstname" name="firstname" value={firstname} onChange={(e) => onInputChange(e)} />
                        {errors.firstname && <p className="text-danger">{errors.firstname}</p>}
                    </div>

                    <div className="col mb-6 input-group">
                        <label htmlFor="lastname" className="input-group-text">Lastname</label>
                        <input type="text" className="form-control" placeholder="Enter lastname" name="lastname" value={lastname} onChange={(e) => onInputChange(e)} />
                        {errors.lastname && <p className="text-danger">{errors.lastname}</p>}
                    </div>

                    <div className="md-6 input-group">
                        <label htmlFor="email" className="input-group-text"><FaEnvelope/></label>
                        <input type="email" className="form-control" placeholder="Enter email" name="email" value={email} onChange={(e) => onInputChange(e)} />
                        {errors.email && <p className="text-danger">{errors.email}</p>}
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
                           

                    <button type="submit" className="col btn btn-outline-dark">Submit</button>
                    <Link className="col btn btn-outline-danger mx-2" to="/members">Cancel</Link>
                    </form>
                
            </div>

        </div>
    );
}
