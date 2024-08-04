/** @type {import('tailwindcss').Config} */
export default {
  content: [
    './index.html',
    './src/**/*.{js,jsx,ts,tsx}',
    './src/components/**/*.{jsx,ts,tsx}', // 添加这行
  ],
  theme: {
    extend: {},
  },
  plugins: [],
}
