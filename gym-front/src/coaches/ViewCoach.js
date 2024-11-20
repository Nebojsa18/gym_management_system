import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';

export default function ViewCoach() {
    const [coach, setCoach] = useState({
        firstname: "",
        lastname: "",
        email: "",
        birthday: "",
        gender: "",
        phone: "",    
    });

    const { id } = useParams();

    useEffect(() => {
        loadCoach();
    }, []);

    const loadCoach = async () => {
        const result = await axios.get(`http://localhost:8080/coach/${id}`);
        setCoach(result.data);
    }

    const formatDate = (dateString) => {
        const date = new Date(dateString);
        const day = date.getDate().toString().padStart(2, '0');
        const month = (date.getMonth() + 1).toString().padStart(2, '0');
        const year = date.getFullYear();
        return `${day}/${month}/${year}`;
    }

    return (
        <div className="coach-container">
            <div className="coach-content-container">
                
                    {/* <h2 className="text-center m-4">Coach Details</h2> */}
                    <div className="card">
                        <div className="card-header">
                        
                            <h5>Details of coach: {coach.firstname} {coach.lastname}</h5>
                        </div>
                        <div className="card-body">
                            <p><b>Firstame:</b> {coach.firstname}</p>
                            <p><b>Lastname:</b> {coach.lastname}</p>
                            <p><b>Username:</b> {coach.username}</p>
                            <p><b>Role:</b> {coach.role}</p>
                            <p><b>Gender:</b> {coach.gender}</p>
                            <p><b>Birthday:</b> {formatDate(coach.birthday)}</p>
                            <p><b>Phone:</b> {coach.phone}</p>
                        </div>
                             
                </div>
                
                <Link className="btn btn-primary my-2" to={"/coaches"}>Go back</Link>
                
            </div>
        </div>
    )
}
