import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { getLoggedCoach} from '../axios_helper';


export default function Home() {

  const loggedCoach=getLoggedCoach();

  return (
    <div className="home-container">
      <div className="content-container">
      {loggedCoach && (
        <h1 className="heading">Welcome to Gym App {loggedCoach.firstname} {loggedCoach.lastname}</h1>
      )}
        <p className="description"></p>
        {/* <button className="cta-button">Otkrijte više</button> */}
      </div>
    </div>
  );

}
