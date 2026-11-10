import React, { useState } from "react";
import Sidebar from "./Sidebar";
import { BRAND, SiteHeader, SiteFooter, Notice } from "../brand";

// 總覽的示意數字（尚未接後端）
const STATS = [
    { label: '使用者 Total User', value: 100, icon: 'M15 19.128a9.38 9.38 0 002.625.372 9.337 9.337 0 004.121-.952 4.125 4.125 0 00-7.533-2.493M15 19.128v-.003c0-1.113-.285-2.16-.786-3.07M15 19.128v.106A12.318 12.318 0 018.624 21c-2.331 0-4.512-.645-6.374-1.766l-.001-.109a6.375 6.375 0 0111.964-3.07M12 6.375a3.375 3.375 0 11-6.75 0 3.375 3.375 0 016.75 0zm8.25 2.25a2.625 2.625 0 11-5.25 0 2.625 2.625 0 015.25 0z' },
    { label: '產品 Total Product', value: 500, icon: 'M8.25 18.75a1.5 1.5 0 01-3 0m3 0a1.5 1.5 0 00-3 0m3 0h6m-9 0H3.375a1.125 1.125 0 01-1.125-1.125V14.25m17.25 4.5a1.5 1.5 0 01-3 0m3 0a1.5 1.5 0 00-3 0m3 0h1.125c.621 0 1.129-.504 1.09-1.124a17.902 17.902 0 00-3.213-9.193 2.056 2.056 0 00-1.58-.86H14.25M16.5 18.75h-2.25m0-11.177v-.958c0-.568-.422-1.048-.987-1.106a48.554 48.554 0 00-10.026 0 1.106 1.106 0 00-.987 1.106v7.635m12-6.677v6.677m0 4.5v-4.5m0 0h-12' },
    { label: '訪客 Total Visitor', value: 500, icon: 'M3.75 3v11.25A2.25 2.25 0 006 16.5h2.25M3.75 3h-1.5m1.5 0h16.5m0 0h1.5m-1.5 0v11.25A2.25 2.25 0 0118 16.5h-2.25m-7.5 0h7.5m-7.5 0l-1 3m8.5-3l1 3m0 0l.5 1.5m-.5-1.5h-9.5m0 0l-.5 1.5m.75-9l3-3 2.148 2.148A12.061 12.061 0 0116.5 7.605' },
];

const Dashboard = () => {

    const [showModel, setShowModel] = useState(false);
    const token = localStorage.getItem('token');


    const toggleModel = () => {
    setShowModel(!showModel);
    };

    const account = localStorage.getItem('account')

    return(
    <div className="flex flex-col min-h-screen">
        <SiteHeader className="fixed top-0 inset-x-0 z-30 pl-[86px]">
            <span className="flex items-center gap-2 text-[15px] font-bold text-ink">
                <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcShta_GXR2xdnsxSzj_GTcJHcNykjVKrCBrZ9qouUl0usuJWG2Rpr_PbTDu3sA9auNUH64&usqp=CAU" alt="" className="w-8 h-8 rounded-full object-cover" />
                <span className="hidden md:inline">{account}</span>
            </span>
        </SiteHeader>
        <Sidebar />
        <main className="flex-grow ml-16 pt-[96px] px-4 md:px-[40px] pb-[40px] flex flex-col gap-[18px]">
            <nav className="flex px-[22px] py-3 text-ink rounded-panel bg-surface" aria-label="Breadcrumb">
                <ol className="inline-flex items-center space-x-1 md:space-x-3">
                    <li className="inline-flex items-center">
                        <a href="#" className="inline-flex items-center text-sm font-bold text-ink">
                            <svg className="w-4 h-4 mr-2" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path d="M10.707 2.293a1 1 0 00-1.414 0l-7 7a1 1 0 001.414 1.414L4 10.414V17a1 1 0 001 1h2a1 1 0 001-1v-2a1 1 0 011-1h2a1 1 0 011 1v2a1 1 0 001 1h2a1 1 0 001-1v-6.586l.293.293a1 1 0 001.414-1.414l-7-7z"></path></svg>
                            Home
                        </a>
                    </li>
                    <li>
                        <div className="flex items-center">
                            <svg className="w-6 h-6 text-ink-muted" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fillRule="evenodd" d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z" clipRule="evenodd"></path></svg>
                            <a href="#" className="ml-1 text-sm font-bold text-ink md:ml-2">Templates</a>
                        </div>
                    </li>
                </ol>
            </nav>
            <div className="grid grid-cols-1 md:grid-cols-3 gap-[18px]">
                {STATS.map(stat => (
                    <div key={stat.label} className="hs-card flex-row items-center">
                        <div className="flex items-center justify-center flex-none w-12 h-12 rounded-tile bg-cream text-ginger-text">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-7 h-7" aria-hidden="true">
                                <path strokeLinecap="round" strokeLinejoin="round" d={stat.icon} />
                            </svg>
                        </div>
                        <div className="hs-readout gap-1 flex-grow">
                            <span className="hs-readout__label text-ink-muted">{stat.label}</span>
                            <span className="hs-readout__value text-[36px]">{stat.value}</span>
                        </div>
                    </div>
                ))}
            </div>
            <Notice art={BRAND.peek} title="八萬發現有東西可以改">
                Change a few things up and try submitting again.
            </Notice>
            <Notice tone="danger" role="alert" title="出了點問題">
                Change a few things up and try submitting again.
            </Notice>
            <div className="hs-notice bg-sage-soft text-sage-text items-center" role="status">
                <span className="hs-tag hs-tag--done">完成</span>
                <p className="hs-notice__body">Change a few things up and try submitting again.</p>
            </div>
        </main>
        <SiteFooter className="ml-16" />
    </div>
    )
}

export default Dashboard;
