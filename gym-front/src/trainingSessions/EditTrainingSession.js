// TrainingSessionForm.js
import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import axios from 'axios'; // Import Axios for making HTTP requests
import {getLoggedCoach} from '../axios_helper';
import '../style/TrainingSessionItems.css';
import { FaTrash,FaPlus,FaCalendar,FaClock,FaCheck,FaExclamationTriangle} from 'react-icons/fa';
import Alert from 'react-bootstrap/Alert';


const AddTrainingSession1 = () => {
  const [date, setDate] = useState('');
  const [time, setTime] = useState('');
  const [coach, setCoach] = useState(getLoggedCoach);
  const [member, setMember] = useState({
    firstname: "",
    lastname: "",
    birthday: "",
    gender: "",
    phone: "",
    email: "",
    
});
  const [exercises, setExercises] = useState([]); // State to hold exercises list
  const [selectedExercise, setSelectedExercise] = useState(null);
  const [numOfReps, setReps] = useState(0);
  const [trainingItems, setTrainingItems] = useState([]); // Initialize with one training item
  const [coaches, setCoaches] = useState([]);
  const [members, setMembers] = useState([]);
  const [loadedTS, setLoadedTS] = useState([]);
  const {id} =useParams();

  const [message, setMessage] = useState("");
  const [showError, setShowError] = useState(false);
  const [showSuccess, setShowSuccess] = useState(false);
  
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
                console.log(response.data);
            } catch (error) {
                console.error("Error loading members", error);
            }
        }
        fetchMembers();
    }, []);

    useEffect(() => {
      // Učitavanje liste Vezbi (ili objekata)
      async function fetchExercises() {
          try {
              const response = await axios.get("http://localhost:8080/exercises");
              setExercises(response.data);
              console.log("Exercises loaded");
          } catch (error) {
              console.error("Error loading exercises:", error);
          }
      }
      fetchExercises();
  }, []);

  useEffect(()=>{
    loadTS()
},[])

  const loadTS = async ()=>{

    const result=await axios.get(`http://localhost:8080/session/${id}`)
    const loadedSession = result.data;
    console.log("SESSIONSSS");
    console.log(loadedSession);
    // Konvertujte datum u format YYYY-MM-DD
    if (loadedSession.date) {
        loadedSession.date = loadedSession.date.substring(0, 10);
    }
    setLoadedTS(loadedSession);
    setLoadedTS(result.data)
    setDate(loadedSession.date);
    setTime(loadedSession.time);
    // setCoach(loadedSession.coach);
    setMember(loadedSession.member);
    setTrainingItems(loadedSession.trainingItems)
    console.log("ITEMI LOADED");
    console.log(loadedSession.trainingItems);
}

    

    // useEffect(() => {
    //     if (loadedTS.coach) {
    //         const selectedCoach = coaches.find(coach => coach.id === loadedTS.coach.id);
    //         setCoach(selectedCoach);
    //     }
    //     if (loadedTS.member) {
    //         const selectedMember = members.find(member => member.id === loadedTS.member.id);
    //         setMember(selectedMember);
    //     }
    // }, [loadedTS]);

  const handleExerciseChange = (event) => {
    const selectedId = parseInt(event.target.value);
    const exercise = exercises.find(ex => ex.id === selectedId);
    setSelectedExercise(exercise);
  };

  

  const handleRepsChange = (event) => {
    const reps = parseInt(event.target.value);
    setReps(reps);

  };

  const handleCoachChange=(event)=>{
    const selectedId = parseInt(event.target.value);
    const coach = coaches.find(ex=> ex.id===selectedId);
    console.log("TRENER U PROMENI")
    console.log(coach);
    setCoach(coach);
  }

  const handleMemberChange=(event)=>{
    const selectedId = parseInt(event.target.value);
    const member = members.find(ex=> ex.id===selectedId);
    console.log("Clan U PROMENI")
    console.log(member);
    setMember(member);
  }

  const handleDateChange=(value) =>{

    const enteredDate = new Date(value);
    console.log(enteredDate);
    const today = new Date();
    console.log(enteredDate > today);
    
    if (enteredDate >= today) {
      setDate(enteredDate);
    } else {
      setErrorItem("Invalid date. Pick a date that isn't before today!");
    }

  }
  
  const [errorItem, setErrorItem] = useState([]);

  const handleAddTrainingItem = () => {
    if (!selectedExercise) {
      setErrorItem("Select item!");
      return;
    }else{
      setErrorItem("");
    }

    if(numOfReps <1){
      // setReps(parseInt(event.target.value));
        setErrorItem("Wrong input. Please select number of reps greater than 0");
        return;
      }else{
        setErrorItem("");
      }


    // Provera da li je već dodata ista vežba
  const existingExercise = trainingItems.find(item => item.exercise.id === selectedExercise.id);

  if (existingExercise) {
    // Ako je već dodata, ažuriraj samo broj ponavljanja
    const updatedTrainingItems = trainingItems.map(item => {
      if (item.exercise.id === selectedExercise.id) {
        return { ...item, numOfReps };
      }
      return item;
    });

    setTrainingItems(updatedTrainingItems);
    setReps(0);
  } else {
    // Ako nije dodata, dodaj novu stavku
    const newTrainingItem = {
      exercise: {
        id: selectedExercise.id,
        exerciseName: selectedExercise.exerciseName
      },
      numOfReps
    };

    setTrainingItems(prevTrainingItems => [...prevTrainingItems, newTrainingItem]);
    setSelectedExercise(null);
    setReps(0);
  }
    
    
  };

  const handleRemoveTrainingItem = (index) => {
    const updatedTrainingItems = [...trainingItems];
    updatedTrainingItems.splice(index, 1);
    setTrainingItems(updatedTrainingItems);
  };
  

  const handleSubmit = (e) => {

    e.preventDefault();
    setShowError(false);
    setShowSuccess(false);
    // Create new TrainingSession object

    if (!member.id) {
      setErrorItem("Please select a member.");
      return;
    }else if (!date || !time) {
    setErrorItem("Please select both date and time.");
    return;
    }else if(trainingItems.length<1){
    setErrorItem("Please add exercises");
    return;
  }else{
    setErrorItem("");
  }
    const newTrainingSession = {
      id,
      date,
      time,
      coach,
      member,
      trainingItems};
        
   
    console.log("Lista itema")
    console.log(newTrainingSession.trainingItems)
    // Send HTTP POST request to create new TrainingSession
    console.log("pre slanja")
    console.log(newTrainingSession)
    
    axios.put(`http://localhost:8080/session/${id}`, newTrainingSession)
      .then(response => {
        // Handle successful creation
        // alert("Session added!");
        setShowSuccess(true);
        setMessage(response.data);

        console.log("dobar"+response.data);
      })
      .catch(error => {
        // Handle error
        setShowError(true);
        setMessage("Cannot delete training session!");
        console.error(error.response);
      });
    // console.log(newTrainingSession);
  };

  return (
    <div className="session-container">
      <div className="session-content-container">
          <h2 className="text-center m-4">Add TrainingSession</h2>
          <form onSubmit={handleSubmit}>
            <div className="row">
                <div className="col mb-3 input-group">
                    <label htmlFor="date" className="input-group-text"><FaCalendar/></label>
                    {/* <input type="date" id="date" className="form-control" placeholder="Enter date" name="date"  onChange={(e) => setDate(e.target.value)} required /> */}
                    <input type="date" id="date" className="form-control" value={date} placeholder="Enter date" name="date"  onChange={(e) => handleDateChange(e.target.value)} required />
                </div>
                <div className="col mb-3 input-group">
                    <label className="input-group-text"><FaClock/></label>
                    <input type="time" step="3600" value={time} onChange={(e) => setTime(e.target.value)}  required />
                </div>
            </div>
            <div className="mb-3 input-group">
                <label className="input-group-text">Coach:</label>
                {/* <select value={coach.id} onChange={(e) => setCoach(e.target.value)} required> */}
                    <select className="form-select" disabled value={coach.id} onChange={handleCoachChange} required>
                    <option value="">Select Coach</option>
                    {coaches.map((coach) => (
                        <option key={coach.id} value={coach.id}>{coach.firstname+ " " + coach.lastname}</option>
                    ))}
                </select>
            </div>
            <div className="mb-3 input-group">
                <label className="input-group-text">Member:</label>
                {/* <select value={member} onChange={(e) => setMember(e.target.value)} required> */}
                <select className="form-select" value={member.id} onChange={handleMemberChange} required>
                    <option value="">Select Member</option>
                    {members.map((member) => (
                        <option key={member.id} value={member.id}>{member.firstname+ " " + member.lastname}</option>
                    ))}
                </select>
            </div>
            <div className="row mb-3">
                <div className="mb-3 col input-group">
                    <label className="input-group-text">Exercise:</label>
                        <select className="form-control" onChange={handleExerciseChange} value={selectedExercise ? selectedExercise.id : ''} required>
                            <option value="">Select Exercise</option>
                            {exercises.map((exercise) => (
                            <option key={exercise.id} value={exercise.id}>{exercise.exerciseName}</option>
                            ))}
                        </select>
                        
                </div>
                    
                <div className="col input-group mb-3">
                   <label className="input-group-text">Reps:</label>
                        {/* <input type="number" value={reps} onChange={(e) => handleRepsChange(e.target.value)}></input> */}
                    <input className="form-control" id="repsInput" type="number" value={numOfReps} onChange={handleRepsChange}></input>  
                    </div>
                    <button type="button" className="btn btn-add" onClick={handleAddTrainingItem}><FaPlus/></button>
                {errorItem && <p className="text-danger">{errorItem}</p>}
            </div>
            <div className='training-item-list'>
                {trainingItems.map((item, index) => (
                    <div key={index} className='item-container'>
                    <div className='item-index'>{index + 1}</div>
                    <div className='item-exercise-name'>{item.exercise.exerciseName}</div>
                    <div className='item-reps'>Reps: {item.numOfReps}</div>
                    <button type="button" className="btn btn-remove px-1" onClick={() => handleRemoveTrainingItem(index)}>
                        <FaTrash /> {/* Dodajte ikonicu kante za smeće */}
                    </button>
                    </div>
                ))}
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
                <button type="submit" className="btn btn-outline-dark mx-2" onClick={handleSubmit} >Update</button>
                <Link link to={"/sessions"} className="btn btn-outline-danger my-2 mx-2" >Cancel</Link>

          </form>
          
        </div>
      </div>


  );
};

export default AddTrainingSession1;
