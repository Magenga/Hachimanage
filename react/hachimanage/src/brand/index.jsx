/**
 * HACHIMAN STUDIO 八萬工作室設計系統的共用元件（樣式在 ./components.css，class 以 hs- 開頭）。
 * 標誌與角色圖 © HACHIMAN STUDIO 八萬工作室，見 ./README.md。
 */
import lockup from './hachiman-lockup.png';
import lockupReverse from './hachiman-lockup-reverse.png';
import peek from './hachiman-peek.png';
import pounce from './hachiman-pounce.png';
import orei from './orei-portrait.png';
import cuddle from './hachiman-orei-cuddle.png';

export const BRAND = {
  name: 'HACHIMAN STUDIO 八萬工作室',
  tagline: '靈感敏捷，作品有溫度。',
  project: 'Hachimanage',
  works: 'https://duicn187532.github.io/HachimanStudio/works.html',
  github: 'https://github.com/Magenga/Hachimanage',
  lockup,
  lockupReverse,
  /** 八萬警覺：發現問題 */
  peek,
  /** 八萬準備撲：開始、執行 */
  pounce,
  /** 歐雷：安心、守護 */
  orei,
  /** 兩隻一起：完成、空白頁 */
  cuddle,
};

/** 頂部導覽：組合字標＋專案名稱，最後一個連結固定是「所有作品」 */
export const SiteHeader = ({ children, className = '' }) => (
  <header className={`hs-header ${className}`}>
    <a className="hs-header__brand" href="#/" aria-label={`${BRAND.name} · ${BRAND.project}`}>
      <img className="hs-lockup--light" src={BRAND.lockup} alt={BRAND.name} />
      <img className="hs-lockup--dark" src={BRAND.lockupReverse} alt={BRAND.name} />
      <span className="hs-header__project">{BRAND.project}</span>
    </a>
    <nav className="hs-header__nav" aria-label="主要導覽">
      {children}
      <a href={BRAND.works} target="_blank" rel="noopener noreferrer">所有作品</a>
    </nav>
  </header>
);

/** 頁尾：工作室全名、標語、作品集與 GitHub 連結（固定墨炭底） */
export const SiteFooter = ({ className = '' }) => (
  <footer className={`hs-footer ${className}`}>
    <div className="hs-footer__brand">
      <span className="hs-footer__name">{BRAND.name}</span>
      <span className="hs-footer__tagline">{BRAND.tagline}</span>
    </div>
    <nav className="hs-footer__links" aria-label="工作室連結">
      <a href={BRAND.works} target="_blank" rel="noopener noreferrer">所有作品</a>
      <a href={BRAND.github} target="_blank" rel="noopener noreferrer">GitHub</a>
    </nav>
  </footer>
);

/** 吉祥物提示框：art 傳 BRAND.peek／BRAND.orei…；tone 為 'dark' 或 'danger' */
export const Notice = ({ art, title, children, tone, role = 'status', className = '' }) => (
  <div className={`hs-notice${tone ? ` hs-notice--${tone}` : ''} ${className}`} role={role}>
    {art && tone !== 'danger' && <img className="hs-notice__art" src={art} alt="" />}
    <div className="hs-notice__text">
      <p className="hs-notice__title">{title}</p>
      {children && <p className="hs-notice__body">{children}</p>}
    </div>
  </div>
);
