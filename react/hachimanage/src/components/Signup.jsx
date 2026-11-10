import React, { useState } from "react";
import axios from "axios";
import { BRAND, Notice } from "../brand";

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
        <div className="flex flex-col gap-[14px] w-[min(100vw-80px,380px)] text-ink">
            <h1 className="text-[26px] leading-tight">建立帳號</h1>
            <label className="hs-field">
                <span className="hs-field__label">帳號 Account</span>
                <input
                    type="text"
                    value={account} onChange={(e) => setAccount(e.target.value)}
                    className="hs-field__input"
                    placeholder="登入用的帳號"
                    autoComplete="username"
                />
            </label>
            <label className="hs-field">
                <span className="hs-field__label">Email</span>
                <input
                    type="email"
                    value={email} onChange={(e) => setEmail(e.target.value)}
                    className="hs-field__input"
                    placeholder="name@example.com"
                    autoComplete="email"
                />
            </label>
            <label className="hs-field">
                <span className="hs-field__label">密碼 Password</span>
                <input
                    type="password"
                    value={password} onChange={(e) => setPassword(e.target.value)}
                    className="hs-field__input"
                    autoComplete="new-password"
                />
            </label>
            <button
                onClick={handleSignup}
                type="submit"
                className="hs-btn hs-btn--primary w-full"
            >
                建立帳號 →
            </button>
            <Notice art={BRAND.orei} title="歐雷幫你顧著帳號" className="bg-surface-2">
                建立帳號即表示你同意<a href="#">服務條款</a>與<a href="#">隱私權政策</a>。
            </Notice>
        </div>
    );
};

export default Signup;
