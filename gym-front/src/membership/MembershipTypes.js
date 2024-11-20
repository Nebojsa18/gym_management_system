import React, { useState, useEffect } from 'react';
import axios from 'axios';
// import '../style/App.css';
import '../style/MembershipType.css';
function MembershipTypes() {
  const [membershipTypes, setMembershipTypes] = useState([]);

  useEffect(() => {
    // Funkcija za dohvatanje podataka o tipovima članstva sa servera
    const fetchMembershipTypes = async () => {
      try {
        const response = await axios.get('http://localhost:8080/membership-types');
        setMembershipTypes(response.data);
      } catch (error) {
        console.error('Error fetching membership types:', error);
      }
    };

    // Pozivamo funkciju za dohvatanje podataka kada se komponenta montira
    fetchMembershipTypes();
  }, []);

  return (
    <div className="membership-container">
      <div className="membership-types-container">
        {/* Prikazivanje tipova članstva u obliku kartica */}
        {membershipTypes.map(membershipType => (
          <div key={membershipType.id} className="card membership-card">
            <h3>{membershipType.name}</h3>
            <h5>Term count: {membershipType.numOfSessions}</h5>
            <h5>Price: {membershipType.price} RSD</h5>
            <p>Description: {membershipType.description}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default MembershipTypes;
