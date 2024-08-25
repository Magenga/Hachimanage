import React, { useState } from "react";
import axios from "axios";

const Signup = ({ toggleModal }) => {
    const [account, setAccount] = useState('');
    const [password, setPassword] = useState('');
    const [email, setEmail] = useState('');

    const handleSignup = async (e) => {
        e.preventDefault();
        try {
            await axios.post('http://localhost:8080/api/user', {
                account,
                password,
                email
            });
            alert("Sign up succeeded");
            toggleModal(); // 註冊成功後關閉彈窗
        } catch (error) {
            alert("Signup failed.");
        }
    };

    return (
        <div className="flex items-center justify-center auto">
            <div className="bg-grey-lighter max-h-screen overflow-y-auto flex flex-col w-full max-w-sm">
                <div className="bg-white px-6 py-8 rounded shadow-md text-black w-full">
                    <h1 className="mb-8 text-3xl text-center">Sign up</h1>
                    <input 
                        type="text"
                        value={account} onChange={(e) => setAccount(e.target.value)}
                        className="block border border-grey-light w-full p-3 rounded mb-4"
                        placeholder="Account for login" 
                    />
                    <input 
                        type="text"
                        value={email} onChange={(e) => setEmail(e.target.value)}
                        className="block border border-grey-light w-full p-3 rounded mb-4"
                        placeholder="Email" 
                    />
                    <input 
                        type="password"
                        value={password} onChange={(e) => setPassword(e.target.value)}
                        className="block border border-grey-light w-full p-3 rounded mb-4"
                        placeholder="Password" 
                    />
                    <button
                        onClick={handleSignup}
                        type="submit"
                        className="w-full text-center py-3 rounded bg-green-500 text-white hover:bg-green-700 focus:outline-none my-1"
                    >
                        Create Account
                    </button>
                    <div className="text-center text-sm text-grey-dark mt-4">
                        By signing up, you agree to the 
                        <a className="no-underline border-b border-grey-dark text-grey-dark" href="#">
                            Terms of Service
                        </a> and 
                        <a className="no-underline border-b border-grey-dark text-grey-dark" href="#">
                            Privacy Policy
                        </a>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Signup;
