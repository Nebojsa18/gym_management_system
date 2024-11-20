import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom'

export default function ViewTrainingSession() {

    const[tSession,setTSession]=useState({
        id: "",
        date: "",
        time:"",
        coach: "",
        member: "",
        trainingItems: []
        
    })

    const{id}=useParams();

    useEffect(()=>{
        loadTSession()
    },[])
    
    const loadTSession=async ()=>{
        const result=await axios.get(`http://localhost:8080/session/${id}`)
        setTSession(result.data);
    }

    return (
        <div className="session-container">
            <div className="session-content-container">
                {/* <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow"> */}
                    <h3 className="text-center m-4"><b>Date:</b> {new Date(tSession.date).toLocaleDateString()} <b>Time:</b> {tSession.time}</h3>
                    <h3 className="text-center m-4"><b>Coach:</b> {tSession.coach}</h3>
                    <h3 className="text-center m-4"><b>Member:</b> {tSession.member}</h3>
                    <div className="row">
                        
                        <div className="mx-auto">
                            <div className="card">
                                <div className="card-body">
                                    {/* <h3>Training Items</h3> */}
                                    {tSession.trainingItems.map((item, index) => (
                                        <div key={index}>
                                            
                                            <ul>
                                                <h5><b>Exercise {index + 1}</b></h5>
                                                <h6><li>{item.exercise.exerciseName} <b>{item.numOfReps} reps</b></li></h6>
                                                {/* <li>{item.numOfReps}</li> */}
                                            </ul>
                                        </div>
                                    ))}
                                
                            </div>
                        </div>
                    </div>
                    <Link className="btn btn-secondary my-2" to={"/sessions"}>
                        Go Back 
                    </Link>
                </div>
            </div>
        </div>
    );
}
