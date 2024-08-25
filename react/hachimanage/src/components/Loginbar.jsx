import React, { useState } from "react";
import Signup from "./Signup";

const Loginbar = ({ account, setAccount, password, setPassword, handleLogin, handleKeyDown }) => {
    const [showModal, setShowModal] = useState(false);

    const toggleModal = () => {
        setShowModal(!showModal);
    };
    

    return (
        <div>
            <div className="w-screen h-screnn max-w-[700px] max-h-[419px] p-6 bg-white rounded-lg border border-[#d9d9d9] flex-col justify-start items-start gap-6 inline-flex">
                <div className="self-stretch h-[70px] flex-col justify-start items-start gap-2 flex">
                    <label htmlFor="account" className="self-stretch text-[#1e1e1e] text-base font-normal leading-snug">Account</label>
                    <input
                        id="account"
                        type="text"
                        value={account}
                        onChange={(e) => setAccount(e.target.value)}
                        className="self-stretch px-4 py-3 bg-white rounded-lg border border-[#d9d9d9] justify-start items-center inline-flex"
                        placeholder="your account"
                        required
                        onKeyDown={handleKeyDown}
                    />
                </div>
                <div className="self-stretch h-[70px] flex-col justify-start items-start gap-2 flex">
                    <label htmlFor="password" className="self-stretch text-[#1e1e1e] text-base font-normal leading-snug">Password</label>
                    <input
                        id="password"
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className="self-stretch px-4 py-3 bg-white rounded-lg border border-[#d9d9d9] justify-start items-center inline-flex"
                        placeholder="your password"
                        required
                        onKeyDown={handleKeyDown}
                    />
                </div>
                <button onClick={handleLogin} className="self-stretch justify-start items-center gap-4 inline-flex">
                    <div className="grow shrink basis-0 h-10 p-3 bg-[#2c2c2c] rounded-lg border border-[#2c2c2c] justify-center items-center gap-2 flex">
                        <div className="text-neutral-100 text-base font-normal leading-none">Sign In</div>
                    </div>
                </button>
                <div className="self-stretch justify-start items-start inline-flex">
                    <button onClick={toggleModal} toggleModal={toggleModal} className="text-[#1e1e1e] text-base font-normal underline leading-snug">
                        Sign Up
                    </button>
                </div>
                <div className="self-stretch justify-start items-start inline-flex">
                    <a href="#" className="text-[#1e1e1e] text-base font-normal underline leading-snug">
                        Forgot password?
                    </a>
                </div>
            </div>

            {showModal && (
                <div className="h-auto fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
                    <div className="h-auto bg-white p-8 rounded-lg shadow-lg">
                        <Signup closeModel={toggleModal} />
                        <button onClick={toggleModal} className="mt-4 bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600">
                            Cancel
                        </button>
                    </div>
                </div>
            )}
        </div>
    );
}

export default Loginbar;
