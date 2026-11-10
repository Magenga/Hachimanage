import React, { useState } from "react";
import Signup from "./Signup";

const Loginbar = ({ account, setAccount, password, setPassword, handleLogin, handleKeyDown }) => {
    const [showModal, setShowModal] = useState(false);

    const toggleModal = () => {
        setShowModal(!showModal);
    };

    return (
        <div>
            <div className="hs-card w-[min(100vw-32px,420px)] gap-[18px]">
                <label className="hs-field" htmlFor="account">
                    <span className="hs-field__label">帳號 Account</span>
                    <input
                        id="account"
                        type="text"
                        value={account}
                        onChange={(e) => setAccount(e.target.value)}
                        className="hs-field__input"
                        placeholder="your account"
                        autoComplete="username"
                        required
                        onKeyDown={handleKeyDown}
                    />
                </label>
                <label className="hs-field" htmlFor="password">
                    <span className="hs-field__label">密碼 Password</span>
                    <input
                        id="password"
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className="hs-field__input"
                        placeholder="your password"
                        autoComplete="current-password"
                        required
                        onKeyDown={handleKeyDown}
                    />
                </label>
                <button onClick={handleLogin} className="hs-btn hs-btn--primary w-full">
                    登入 Sign In →
                </button>
                <div className="flex items-center justify-between gap-3 text-sm">
                    <button type="button" onClick={toggleModal} className="font-bold text-ginger-text underline underline-offset-4">
                        建立帳號 Sign Up
                    </button>
                    <a href="#" className="text-ink-muted">
                        忘記密碼？
                    </a>
                </div>
            </div>

            {showModal && (
                <div className="h-auto fixed inset-0 bg-[rgba(59,49,42,0.6)] flex items-center justify-center z-50 p-4">
                    <div className="h-auto bg-surface p-[22px] rounded-card max-h-screen overflow-y-auto">
                        <Signup toggleModal={toggleModal} />
                        <button onClick={toggleModal} className="hs-btn hs-btn--secondary w-full mt-3">
                            取消 Cancel
                        </button>
                    </div>
                </div>
            )}
        </div>
    );
}

export default Loginbar;
