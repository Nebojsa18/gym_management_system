// import axios from 'axios';
// import React, { useState, useEffect } from 'react';
// import { Link, useNavigate } from 'react-router-dom';

// export default function AddTrainingSession() {

//     let navigate = useNavigate()

//     const [trainingSession, setTrainingSession] = useState({
//         date: "",
//         time: "",
//         coach: "",
//         member: "",
//         trainingItems: []
//     });

//     const [trainingItem,setTrainingItem]=useState({
//         excercise: "",
//         reps: ""
//     });

//     const [coaches, setCoaches] = useState([]);

//     useEffect(() => {
//         // Učitavanje liste trenera (ili objekata)
//         async function fetchCoaches() {
//             try {
//                 const response = await axios.get("http://localhost:8080/coaches");
//                 setCoaches(response.data);
//                 console.log(response.data);
//             } catch (error) {
//                 console.error("Error loading coaches:", error);
//             }
//         }
//         fetchCoaches();
//     }, []);

//     const [excercise, setExcercise] = useState("");

//     const [excercises, setExcercises] = useState([]);

//     useEffect(() => {
//         // Učitavanje liste vezbi (ili objekata)
//         async function fetchExcercises() {
//             try {
//                 const response = await axios.get("http://localhost:8080/excercises");
//                 setExcercises(response.data);
//                 console.log(response.data);
//             } catch (error) {
//                 console.error("Error loading excercises:", error);
//             }
//         }
//         fetchExcercises();
//     }, []);

//     const { date,time,coach,member,trainingItems } = trainingSession;

    
//     const hours = Array.from({ length: 24 }, (_, i) => i); 
//     const [hour, setHour] =useState("");
      

//     const onInputChange = (e) => {
//         // setTrainingSession({ ...coach, [e.target.name]: e.target.value });
//         // setExcercise(e.target.value);
//         const { name, value } = e.target;
//         if (name === "reps") {
//             setReps(value);
//         } else if(name ==="excercise"){
//             setExcercise(e.target.value);
//         }
//             // else{setTrainingSession({ ...trainingSession, [name]: value });
//             else if(name ==="date"){
//                 const enteredDate = new Date(value);
//                 const today = new Date();
//                 if (enteredDate < today) {
//                     setTrainingSession({ ...trainingSession, [name]: value });
//                 } else {
//                     // Ako je uneti datum današnji ili kasniji, možete obavestiti korisnika
//                     alert("Invalid date. Pick a date that isn't before today!");
//                     // Ili preduzeti odgovarajuće radnje
//                 }
//             }
//         }
    
//     };

//     const onSubmit=async(e)=>{
//         e.preventDefault();
//         try {
//             const response = await axios.post("http://localhost:8080/add-session", trainingSession);
//             navigate("/");
//         } catch (error) {
//             if (error.response) {
//                 setError(error.response.data);
//                 window.alert(error.response.data);
//             } else {
//                 setError("An unexpected error occurred.");
//                 window.alert("An unexpected error occurred.");
//             }
//         }
        

//     }

//     const [error, setError] = useState("");

//     const [reps, setReps] = useState("");

//     const onAddTrainingItem = () => {
//         if (excercise && reps) {
//             const newTrainingItem = {
//                 excercise: excercise,
//                 reps: reps
//             };
//             // 
//             setTrainingSession(prevState => ({
//                 ...prevState,
//                 trainingItems: [...prevState.trainingItems, newTrainingItem]
//             }));
//             // Resetujemo excercise i reps nakon dodavanja trainingItem-a
//             setExcercise("");
//             setReps("");
//             console.log(trainingSession);
//         } else {
//             // Ako nisu uneti excercise i reps, možemo prikazati poruku o grešci ili obavestiti korisnika na drugi način
//             console.error("Excercise and reps are required.");
//         }
//     };

//     return (
//         <div className="container">
//             <div className="row">
//                 <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
//                     <h2 className="text-center m-4">Add TrainingSession</h2>

//                     <form onSubmit={(e) => onSubmit(e)}>
//                      <div className="mb-3">
//                         <label htmlFor="date" className="form-label">Date</label>
//                         <input type="date" id="date" className="form-control" placeholder="Enter date" name="date" value={date} onChange={(e) => onInputChange(e)} />
//                      </div>
                      
//                     <div className="mb-3">
//                         <label htmlFor="coach" className="form-label">Coach</label>
//                         <select className="form-control" name="coach" value={coach} onChange={(e) => onInputChange(e)}>
//                             <option value="">Select Coach</option>
//                             {coaches.map((coach) => (
//                                 <option key={coach.id} value={coach.id}>{coach.firstname+ " " + coach.lastname}</option>
//                             ))}
//                         </select>
//                     </div>
//                     <div className="mb-3">
//                         <label htmlFor="member" className="form-label">Member</label>
//                         <select className="form-control" name="member" value={member} onChange={(e) => onInputChange(e)}>
//                             <option value="">Select Member</option>
//                             {coaches.map((member) => (
//                                 <option key={member.id} value={member.id}>{member.firstname+ " " + member.lastname}</option>
//                             ))}
//                         </select>
//                     </div>
//                     <div className="mb-3">
//                         <label htmlFor="excercise" className="form-label">Excercise</label>
//                         <select className="form-control" name="excercise" value={excercise} onChange={(e) => onInputChange(e)}>
//                             <option value="">Select excercise</option>
//                             {excercises.map((excercise) => (
//                                 <option key={excercise.id} value={excercise.id}>{excercise.excerciseName}</option>
//                             ))}
//                         </select>
//                     </div>                
//                     <div className="mb-3">
//                         <label htmlFor="reps" className="form-label">Reps</label>
//                         <input type="number" id="reps" className="form-control" placeholder="Enter reps" name="reps" value={reps} onChange={(e) => onInputChange(e)} />
//                     </div>      
//                     <button type="button" className="btn btn-outline-primary" onClick={onAddTrainingItem}>Add Training Item</button>
          
//                     {/*// <div className="mb-3">
//                     //     <label htmlFor="lastname" className="form-label">Lastname</label>
//                     //     <input type="text" className="form-control" placeholder="Enter lastname" name="lastname" value={lastname} onChange={(e) => onInputChange(e)} />
//                     // </div>

//                     // <div className="mb-3">
//                     //     <label htmlFor="birthday" className="form-label">Birthday</label>
//                     //     <input type="date" className="form-control" placeholder="Enter birthday" name="birthday" value={birthday} onChange={(e) => onInputChange(e)} />
//                     // </div>

//                     // <div className="mb-3">
//                     //     <label htmlFor="gender" className="form-label">Gender</label>
//                     //     <select className="form-control" name="gender" value={gender} onChange={(e) => onInputChange(e)}>
//                     //         <option value="">Select gender</option>
//                     //         <option value="MALE">MALE</option>
//                     //         <option value="FEMALE">FEMALE</option>
//                     //     </select>
//                     // </div>

//                     // <div className="mb-3">
//                     //     <label htmlFor="phone" className="form-label">Phone</label>
//                     //     <input type="text" className="form-control" placeholder="Enter phone number" name="phone" value={phone} onChange={(e) => onInputChange(e)} />
//                     // </div>

//                     // <div className="mb-3">
//                     //     <label htmlFor="username" className="form-label">Username</label>
//                     //     <input type="text" className="form-control" placeholder="Enter username" name="username" value={username} onChange={(e) => onInputChange(e)} />
//                     // </div>

//                     // <div className="mb-3">
//                     //     <label htmlFor="password" className="form-label">Password</label>
//                     //     <input type="password" className="form-control" placeholder="Enter password" name="password" value={password} onChange={(e) => onInputChange(e)} />
//                     // </div> */}

//                     <button type="submit" className="btn btn-outline-primary">Submit</button>
//                     <Link className="btn btn-outline-danger mx-2" to="/">Cancel</Link>
//                     </form>
//                 </div>
//             </div>

//         </div>
//     );
// }
// //   const handleTrainingItemChange = (index, field, value) => {
// //     const updatedItems = [...trainingItems];
// //     updatedItems[index][field] = value;
// //     setTrainingItems(updatedItems);
// //     console.log(trainingItems);
// //   };

// // const handleTrainingItemChange = (index, field, value) => {
// //     const updatedItems = [...trainingItems];
// //     if (field === 'exercise') {
// //         updatedItems[index][field] = value ? value : null; // Postavljamo celu vežbu, ili null ako nije izabrana
// //     } else {
// //         updatedItems[index][field] = value; // Ažuriranje ostalih polja
// //     }
// //     setTrainingItems(updatedItems);
// // };


// //   const handleAddTrainingItem = () => {
// //     setTrainingItems([...trainingItems, { exerciseId: '', numOfReps: '' }]);
// //   };
