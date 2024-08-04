import React from "react"


const Loginbar = ({ account, setAccount, password, setPassword, handleLogin, handleKeyDown }) =>{
    return(
        <div>
            <div className="w-[700px] h-[419px] p-6 bg-white rounded-lg border border-[#d9d9d9] flex-col justify-start items-start gap-6 inline-flex">
                <div className="self-stretch h-[70px] flex-col justify-start items-start gap-2 flex">
                    <label htmlFor="account" className="self-stretch text-[#1e1e1e] text-base font-normal font-['Inter'] leading-snug">Account</label>
                    <input id="account" type="text" value={account} onChange={(e) => setAccount(e.target.value)} className="self-stretch px-4 py-3 bg-white rounded-lg border border-[#d9d9d9] justify-start items-center inline-flex" placeholder="your account" required onKeyDown={handleKeyDown}/>
                </div>
                <div className="self-stretch h-[70px] flex-col justify-start items-start gap-2 flex">
                    <label htmlFor="password" className="self-stretch text-[#1e1e1e] text-base font-normal font-['Inter'] leading-snug">Password</label>
                    <input id="password" type="password" value={password} onChange={(e) => setPassword(e.target.value)} className="self-stretch px-4 py-3 bg-white rounded-lg border border-[#d9d9d9] justify-start items-center inline-flex" placeholder="your password" required onKeyDown={handleKeyDown}/>
                </div>
                <button onClick={handleLogin} className="self-stretch justify-start items-center gap-4 inline-flex">
                    <div className="grow shrink basis-0 h-10 p-3 bg-[#2c2c2c] rounded-lg border border-[#2c2c2c] justify-center items-center gap-2 flex">
                    <div className="text-neutral-100 text-base font-normal font-['Inter'] leading-none">Sign In</div>
                    </div>
                </button>
                <div className="self-stretch justify-start items-start inline-flex">
                    <a href="#" className="text-[#1e1e1e] text-base font-normal font-['Inter'] underline leading-snug">Forgot password?</a>
                </div>
            </div>
        </div>
    )
}

export default Loginbar;