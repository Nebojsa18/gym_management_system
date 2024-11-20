import React ,{ useContext, useState } from 'react'
import { Link } from 'react-router-dom'
import '../style/Navbar.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import logo from '../images/logo.png';
import { jwtDecode } from "jwt-decode";
import { getAuthToken , isAuthenticated,getLoggedCoach, setLoggedCoach,setIsAuthenticated, isOwner,setIsOwner} from '../axios_helper';
import { FaUser, FaBars, FaXmark } from 'react-icons/fa';

import Context from '../components/Context';
import { useNavigate } from 'react-router-dom';

export default function Navbar() {

    const [showMenu, setShowMenu] = useState(false);

    const toggleMenu = () => {
      setShowMenu(!showMenu);
      console.log(!showMenu);
    };
   
     const closeMenuOnMobile = () => {
       if (window.innerWidth <= 1150) {
         setShowMenu(false);
       }
     };

    const context = useContext(Context);
    // const loggedCoach=context.loggedCoach;
    const loggedCoach=getLoggedCoach();
    // console.log(loggedCoach+ loggedCoach);
    // const setLoggedCoach=context.setLoggedCoach;

    const navigate = useNavigate(); 
    const [showUserInfo, setShowUserInfo] = useState(false);

    const handleUserInfo = () => {
      setShowUserInfo(!showUserInfo);
    };
    
  const logout = async () => {
    try {
        console.log("pre brisanja" + getAuthToken());
        localStorage.removeItem('auth_token'); // Očisti token iz lokalnog skladišta
        // context.setLoggedCoach(null);
        // navigate("/"); 
        localStorage.removeItem('logged_coach');
        
      } catch (error) {
        console.error('Greška pri odjavljivanju:', error);
    }
};


  const handleLogout = async () => {
      await logout(); // Pozovi funkciju za odjavljivanje
  };

//   const handleCoach =() => {
//     console.log(loggedCoach.id);
//     navigate(`/view-coach/${loggedCoach.id}`); 
// };

  return (
      <div>
          <nav className="navbar">
        
          <Link to="/home" className="navbar-logo">
                      
                      <img src={logo} alt="logo" width={100} height={100}  />
            </Link>
            <ul className="nav__list">
              <div className={`navbar-container${showMenu ? "-show-menu" : ""}`}>  
                  { loggedCoach && (
                    <ul className="nav-group">
                      <li className="nav-item">
                          <Link className="nav-links" >
                              Coaches
                          </Link >
                          <li className="nav-dropdown">
                              <Link className="nav-dropdown-item" to="/coaches" onClick={closeMenuOnMobile}>
                                  List of Coaches
                              </Link>
                              <Link className="nav-dropdown-item" to="/add-coach" onClick={closeMenuOnMobile}>
                                  Add Coach
                              </Link>
                          </li>
                      </li>
                  </ul>
                  )}
                  {loggedCoach && (
                  <ul className="nav-group">
                      <li className="nav-item">
                          <Link className="nav-links" >
                              Members
                          </Link>
                          <li className="nav-dropdown">
                              <Link className="nav-dropdown-item" to="/members" onClick={closeMenuOnMobile}>
                                  List of Members
                              </Link>
                              <Link className="nav-dropdown-item" to="/add-member" onClick={closeMenuOnMobile}>
                                  Add Member
                              </Link>
                              {/* <Link className="nav-dropdown-item" to="/add-payment">
                                  Add Payment
                              </Link> */}
                          </li>
                      </li>
                  </ul>
                  )}
                {loggedCoach && (
                  <ul className="nav-group">
                      <li className="nav-item">
                          <Link className="nav-links">
                              Training Sessions
                          </Link>
                          <li className="nav-dropdown">
                              <Link className="nav-dropdown-item" to="/sessions" onClick={closeMenuOnMobile}>
                                  List of Training Sessions
                              </Link>
                              <Link className="nav-dropdown-item" to="/add-session" onClick={closeMenuOnMobile}>
                                  Add Session
                              </Link>
                          </li>
                      </li>
                  </ul>
                )}
                {loggedCoach && (
                  <ul className="nav-group">
                    <li>
                        <Link className="nav-links" to="/add-payment" onClick={closeMenuOnMobile}>
                              Add Payment
                        </Link>
                    </li>
                  </ul>
                    )}
                    {loggedCoach && (
                  <ul className="nav-group">
                    <li className="nav-item">
                        <Link className="nav-links" onClick={closeMenuOnMobile}>
                              Membership Types
                        </Link>
                        <li className="nav-dropdown">
                              <Link className="nav-dropdown-item" to="/membership-types" onClick={closeMenuOnMobile}>
                                  List of Membership Types
                              </Link>
                              <Link className="nav-dropdown-item" to="/add-membership-type" onClick={closeMenuOnMobile}>
                                  Add Membership Type
                              </Link>
                              
                          </li>
                    </li>
                  </ul>
                    )}
                {loggedCoach ? (
                  <ul className="nav-group user">
                      <li className="nav-item icon">
                      <FaUser className="user-icon"/>
                          <li className="nav-dropdown ">
                            <p className="nav-user">{loggedCoach.firstname} {loggedCoach.lastname} ({loggedCoach.role})</p>
                        {/* <Link onClick={handleCoach} className="nav-links">{loggedCoach.firstname} {loggedCoach.lastname} ({loggedCoach.role})</Link> */}
                        <Link onClick={handleLogout} className="nav-logout" to="/" >Log Out</Link>
                          </li>
                      </li>
                  </ul>
                ) : (
                       <span className='nav-user'></span>
                    )}
                <div className="nav__close" id="nav-close" onClick={toggleMenu}>
                    <FaBars />
                    
                </div>
              </div>
              </ul>
              <div className="nav__toggle" id="nav-toggle" onClick={toggleMenu}>
                    <FaBars style={{color: "#ffffff"}}/>
                </div>
          </nav>
      </div>
  );
}
