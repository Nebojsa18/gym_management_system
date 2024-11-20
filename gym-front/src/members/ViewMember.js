import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';

export default function ViewMember() {
    const [member, setMember] = useState({
        firstname: "",
        lastname: "",
        email: "",
        birthday: "",
        gender: "",
        phone: "",    
    });

    const { id } = useParams();
    const [membershipPayments, setMembershipPayments] = useState([]);
    const [showPayments, setShowPayments] = useState(false);
    const [buttonText, setButtonText] = useState("View Payments");

    useEffect(() => {
        loadMember();
        loadMembershipPayments();
    }, []);

    const loadMember = async () => {
        const result = await axios.get(`http://localhost:8080/member/${id}`);
        setMember(result.data);
    }

    const loadMembershipPayments = async () => {
        const result = await axios.get(`http://localhost:8080/member/${id}/membership-payments`);
        setMembershipPayments(result.data);
    }

    const togglePayments = () => {
        setShowPayments(!showPayments);
        setButtonText(showPayments ? "View Payments" : "Close Payments");
    }

    const formatDate = (dateString) => {
        const date = new Date(dateString);
        const day = date.getDate().toString().padStart(2, '0');
        const month = (date.getMonth() + 1).toString().padStart(2, '0');
        const year = date.getFullYear();
        return `${day}/${month}/${year}`;
    }

    const getMonthName = (paymentDate) => {
        const months = [
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        ];
        const date = new Date(paymentDate);
        const monthIndex = date.getMonth();
        return months[monthIndex] + "-" +months[monthIndex+1];
    };
    

    return (
        
        <div className="member-container">
            <div className="member-content-container">
                {/* <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow"> */}
                {/* <h2 className="text-center m-4">User Details</h2> */}
                    {!showPayments && (
                        
                    <div className="card mb-4">
                        <div className="card-header">
                            <h5>Details of: {member.firstname} {member.lastname}</h5>
                        </div>
                            <div className="card-body">
                                <p><b>Name:</b> {member.firstname}</p>
                                <p><b>Lastname:</b> {member.lastname}</p>
                                <p><b>Gender:</b> {member.gender}</p>
                                <p><b>Birthday:</b> {formatDate(member.birthday)}</p>
                                <p><b>Email:</b> {member.email}</p>
                                <p><b>Phone:</b> {member.phone}</p>
                            </div>
                    </div>
                    )}
                    {showPayments && (
                    <div className="member-payment-content-container">
                        <h4 className="text-center m-4">Membership Payments of {member.firstname} {member.lastname}</h4>
                        {membershipPayments.map(payment => (
                            <div key={payment.id} className="card mb-4">
                                <div className="card-header">
                                    <h5>{getMonthName(payment.paymentDate)}</h5>
                                </div>
                                <div className="card-body">
                                    <p><b>Payment Date: </b> {formatDate(payment.paymentDate)}</p>
                                    <p><b>Valid Until: </b> {formatDate(payment.validUntil)}</p>
                                    <p><b>Charged by: </b>{payment.coach.firstname + " " + payment.coach.lastname}</p>
                                    <p><b>Remaining Sessions: </b> {payment.remainingSessions}</p>
                                    <p><b>Used Sessions: </b> {payment.usedSessions}</p>
                                </div>
                            </div>
                        ))}
                    </div>
                )}
                    
                    
                    <div className="row mt-4">
                    <div className="col-md-6 offset-md-3">
                    <button className="btn btn-primary my-2 m-2" onClick={togglePayments}>{buttonText}</button>
                    <Link className="btn btn-secondary my-2  m-2" to={"/members"}>Go back</Link>
                    </div>
                </div>
                {/* </div> */}
                
                
            </div>
        </div>
    )
}
