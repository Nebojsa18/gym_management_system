import React, { useState,useContext } from 'react'; 
import axios from 'axios'; 
import { useNavigate } from 'react-router-dom'; 
import { 
	MDBContainer, 
	MDBInput, 
	MDBBtn, 
} from 'mdb-react-ui-kit'; 

import {request, setAuthToken,setIsAuthenticated, getAuthToken,setLoggedCoach} from '../axios_helper';
import Context from './Context';
import '../App.css';


function LoginPage() { 
	const [username, setUsername] = useState(''); 
	const [password, setPassword] = useState(''); 
	const [error, setError] = useState(''); 
	const navigate = useNavigate(); 
	const context = useContext(Context);
    // const loggedCoach=context.loggedCoach;
    // const setLoggedCoach=context.setLoggedCoach;
	

	const handleLogin = async () => { 
		try { 
			if (!username || !password) { 
				setError('Please enter both username and password.'); 
				return; 
			} 

			const response = await axios.post('http://localhost:8080/login', { username, password }); 
			// const response = await request('POST', 'http://localhost:8080/login', { username, password });
			console.log('Login successful:', response.data); 
			setLoggedCoach(response.data);
			setAuthToken(response.data.token);
			navigate('/home'); 
		} catch (error) { 
			console.error('Login failed:', error.response ? error.response.data : error.message); 
			setError('Login failed.');
			// alert('Invalid username or password.');
			 
		} 
	}; 

	return ( 
		<div className="login-container"> 
			<div className="content-container" style={{ width: '500px', height: 'auto' }}> 
				<MDBContainer className="p-3"> 
					<h2 className="mb-4 text-center">Login Page</h2> 
					<MDBInput wrapperClass='mb-4' placeholder='Username' id='username' value={username} type='text' onChange={(e) => setUsername(e.target.value)} /> 
					<MDBInput wrapperClass='mb-4' placeholder='Password' id='password' type='password' value={password} onChange={(e) => setPassword(e.target.value)} /> 
					{error && <p className="text-danger">{error}</p>} {/* Render error message if exists */} 
					<button className="cta-button" style={{ height:'50px',width: '100%' }} onClick={handleLogin}>Sign in</button> 
					
				</MDBContainer> 
			</div> 
		</div> 
	); 
} 

export default LoginPage; 