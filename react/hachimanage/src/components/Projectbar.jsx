
const Projectbar = () =>{


    return (
        <div>
            			<aside className="h-screen w-16 fixed transition-transform ease-in-out duration-1000 z-50 flex flex-col bg-[#1E293B] overflow-y-auto hide-scrollbar">
				<div className="mt-20 flex flex-col space-y-4">
					<div onClick={backToBoard} className="group flex items-center justify-center text-white w-full bg-[#1E293B] p-3 rounded-full transition-colors duration-300 hover:bg-gray-700 cursor-pointer">
						<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
							<path strokeLinecap="round" strokeLinejoin="round" d="M2.25 12l8.954-8.955c.44-.439 1.152-.439 1.591 0L21.75 12M4.5 9.75v10.125c0 .621.504 1.125 1.125 1.125H9.75v-4.875c0-.621.504-1.125 1.125-1.125h2.25c.621 0 1.125.504 1.125 1.125V21h4.125c.621 0 1.125-.504 1.125-1.125V9.75M8.25 21h8.25" />
						</svg>
					</div>
					<div onClick={toggleModal} className="group flex items-center justify-center text-white w-full bg-[#1E293B] p-3 rounded-full transition-colors duration-300 hover:bg-gray-700 cursor-pointer">
						<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
							<path strokeLinecap="round" strokeLinejoin="round" d="M12 4.75V19.25M4.75 12H19.25" />
						</svg>
					</div>
					{projectList.map((projectId, index) => (
						<div onClick={() => goChat(projectId)} key={index} className="group flex items-center justify-center text-white w-full bg-[#1E293B] p-3 rounded-full transition-colors duration-300 hover:bg-gray-700 cursor-pointer">
							<div className="flex flex-col items-center">
								<span className="mt-2">{projectId}</span>
							</div>
						</div>
					))}
					<div onClick={logOut} className="group flex items-center justify-center text-white w-full bg-[#1E293B] p-3 rounded-full transition-colors duration-300 hover:bg-gray-700 cursor-pointer sticky bottom-0">
						<svg width="24" height="24" viewBox="0 0 48 48" fill="none" xmlns="http://www.w3.org/2000/svg" className="w-6 h-6">
							<rect width="48" height="48" fill="none"/>
							<path d="M18 42H10C8.93913 42 7.92172 41.5786 7.17157 40.8284C6.42143 40.0783 6 39.0609 6 38V10C6 8.93913 6.42143 7.92172 7.17157 7.17157C7.92172 6.42143 8.93913 6 10 6H18M32 34L42 24M42 24L32 14M42 24H18" stroke="#F3F3F3" strokeWidth="4" strokeLinecap="round" strokeLinejoin="round"/>
						</svg>
					</div>
				</div>
			</aside>
        </div>
    )
}