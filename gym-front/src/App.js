import './App.css';
// import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Navbar from './layout/Navbar';
import Home from './pages/Home';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Navigate } from "react-router-dom";
import { useState, useEffect } from "react";

import ListCoaches from './coaches/ListCoaches';
import AddCoach from './coaches/AddCoach';
import EditCoach from './coaches/EditCoach';
import ViewCoach from './coaches/ViewCoach';

import AddMember from './members/AddMember';
import ListMembers from './members/ListMembers';
import EditMember from './members/EditMember';
import ViewMember from './members/ViewMember';

import AddTrainingSession1 from './trainingSessions/AddTrainingSession1';
import ListTrainingSessions from './trainingSessions/ListTrainingSessions';
import ViewTrainingSession from './trainingSessions/ViewTrainingSession';
import EditTrainingSession from './trainingSessions/EditTrainingSession';

import AddNewPayment from './membershipPayments/AddNewPayment';
import LoginPage from './components/LoginPage';
import { jwtDecode } from "jwt-decode";
import { getAuthToken , isAuthenticated, setIsAuthenticated, isOwner,setIsOwner, getLoggedCoach} from './axios_helper';
import Context from './components/Context';
import MembershipTypes from './membership/MembershipTypes';
import AddMembershipType from './membership/AddMembershipType';

function App() {

  
  // const[loggedCoach,setLoggedCoach] = useState("");
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [loggedCoach, setLoggedCoach] = useState(null);

  const loggedCoachGet =() =>{
    console.log("ulogovan " +loggedCoach);
    return loggedCoach;

  }

  useEffect(() => {
    
    // const storedCoach = localStorage.getItem("loggedCoach");
    // if (storedCoach) {
    //   setLoggedCoach(storedCoach);
    // }

    let token = getAuthToken();

    // if (token !== null) {

    //   const decoded = jwtDecode(token);

    //   if (decoded.role === "OWNER") {
    //     setIsOwner(true);
    //   } else {
    //     setIsOwner(false);
    //   }
    //   setLoggedCoach(getLoggedCoach());
    // } else {
    //   setIsAuthenticated(false);
    //   setIsOwner(false);
    // }

    
    
  }, []);


  

  return (
    <div className="App">
      <Router>
      {/* <Context.Provider value={{loggedCoach,setLoggedCoach}}> */}
      <Navbar/>
      <Routes>
        <Route exact path='/' element={<LoginPage/>}></Route>
        <Route exact path="/home" element={<Home/>}></Route>
        



        <Route exact path="/coaches" element={<ListCoaches/>}></Route>
        {/* <Route exact path="/coaches" element={loggedCoachGet() ? <ListCoaches /> : <Navigate to="/" />}> </Route> */}
        <Route exact path="/add-coach" element={<AddCoach/>}></Route>
        <Route exact path="/edit-coach/:id" element={<EditCoach/>}></Route>
        <Route exact path="/view-coach/:id" element={<ViewCoach/>}></Route>

        <Route exact path="/add-member" element={<AddMember/>}></Route>
        <Route exact path="/members" element={<ListMembers/>}></Route>
        <Route exact path="/edit-member/:id" element={<EditMember/>}></Route>
        <Route exact path="/view-member/:id" element={<ViewMember/>}></Route>

        <Route exact path="/add-session" element={<AddTrainingSession1/>}></Route>
        <Route exact path="/sessions" element={<ListTrainingSessions/>}></Route>
        <Route exact path="/view-session/:id" element={<ViewTrainingSession/>}></Route>
        <Route exact path="/edit-session/:id" element={<EditTrainingSession/>}></Route>

        <Route exact path="/add-payment" element={<AddNewPayment/>}></Route>
        <Route exact path="/membership-types" element={<MembershipTypes/>}></Route>
        <Route exact path="/add-membership-type" element={<AddMembershipType/>}></Route>

      </Routes>
      {/* </Context.Provider> */}
      </Router>
    </div>
  );
}

export default App;
