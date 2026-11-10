/** @type {import('tailwindcss').Config} */
// HACHIMAN STUDIO 八萬工作室設計系統：顏色名稱與設計系統的 tokens 一致，值都指向 src/brand/tokens.css 的 CSS 變數，
// 深色主題由 <html data-theme="dark"> 切換（index.html 依系統設定加上）。
const t = name => `var(--${name})`;
export default {
  content: ['./index.html', './src/**/*.{js,jsx,ts,tsx}'],
  theme: {
    extend: {
      colors: {
        ginger: t('hachiman-ginger'),
        cream: t('cream'),
        'cream-deep': t('cream-deep'),
        charcoal: t('charcoal'),
        'orei-white': t('orei-white'),
        sage: t('sage'),
        surface: t('surface'),
        'surface-2': t('surface-2'),
        line: t('line'),
        'control-border': t('control-border'),
        ink: t('ink'),
        'ink-muted': t('ink-muted'),
        'ginger-text': t('ginger-text'),
        action: t('action'),
        'on-action': t('on-action'),
        'ginger-soft': t('ginger-soft'),
        'sage-text': t('sage-text'),
        'sage-soft': t('sage-soft'),
        'danger-text': t('danger-text'),
        'danger-soft': t('danger-soft'),
      },
      fontFamily: {
        sans: ['Lato', '"Noto Sans TC"', 'system-ui', 'sans-serif'],
        zh: ['"Noto Sans TC"', 'Lato', 'system-ui', 'sans-serif'],
      },
      borderRadius: {
        xs: '6px',
        control: '12px',
        button: '14px',
        tile: '16px',
        panel: '20px',
        card: '24px',
        hero: '28px',
      },
    },
  },
  plugins: [],
}
