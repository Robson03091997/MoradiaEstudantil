import React, { useState, useRef, useEffect } from 'react';
import { Link, useNavigate, useLocation } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';
import { 
  FaUser, 
  FaSignOutAlt, 
  FaBars, 
  FaTimes, 
  FaHome, 
  FaBuilding, 
  FaChartBar, 
  FaPlus, 
  FaChevronDown
} from 'react-icons/fa';

const Header = () => {
  const { user, logout } = useAuth();
  const navigate = useNavigate();
  const location = useLocation();
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const [isUserDropdownOpen, setIsUserDropdownOpen] = useState(false);
  const userDropdownRef = useRef(null);

  const handleLogout = () => {
    logout();
    navigate('/');
    setIsUserDropdownOpen(false);
  };

  const toggleMenu = () => {
    setIsMenuOpen(!isMenuOpen);
  };

  const toggleUserDropdown = () => {
    setIsUserDropdownOpen(!isUserDropdownOpen);
  };

  // Close dropdown when clicking outside
  useEffect(() => {
    const handleClickOutside = (event) => {
      if (userDropdownRef.current && !userDropdownRef.current.contains(event.target)) {
        setIsUserDropdownOpen(false);
      }
    };

    document.addEventListener('mousedown', handleClickOutside);
    return () => {
      document.removeEventListener('mousedown', handleClickOutside);
    };
  }, []);

  // Close mobile menu when route changes
  useEffect(() => {
    setIsMenuOpen(false);
  }, [location.pathname]);

  const isActive = (path) => {
    return location.pathname === path;
  };

  const navigationItems = [
    { path: '/', label: 'Início', icon: FaHome },
    { path: '/properties', label: 'Imóveis', icon: FaBuilding },
  ];

  const userNavigationItems = user ? [
    { path: '/dashboard', label: 'Dashboard', icon: FaChartBar },
    { path: '/my-properties', label: 'Meus Imóveis', icon: FaBuilding },
    { path: '/add-property', label: 'Anunciar', icon: FaPlus },
    { path: '/statistics', label: 'Estatísticas', icon: FaChartBar },
  ] : [];

  return (
    <header className="header-modern">
      <div className="header-container">
        {/* Main Header Content */}
        <div className="header-main">
          
          {/* Logo Section */}
          <div className="header-logo">
            <Link to="/" className="logo-link">
              <div className="logo-icon">🏠</div>
              <span className="logo-text">ImóveisMC322</span>
            </Link>
          </div>

          {/* Desktop Navigation */}
          <nav className="header-nav">
            {navigationItems.map((item) => {
              const Icon = item.icon;
              return (
                <Link 
                  key={item.path}
                  to={item.path} 
                  className={`nav-item ${isActive(item.path) ? 'nav-item-active' : ''}`}
                >
                  <Icon className="nav-icon" />
                  <span>{item.label}</span>
                </Link>
              );
            })}
            
            {/* User-specific navigation */}
            {user && userNavigationItems.map((item) => {
              const Icon = item.icon;
              return (
                <Link 
                  key={item.path}
                  to={item.path} 
                  className={`nav-item ${isActive(item.path) ? 'nav-item-active' : ''}`}
                >
                  <Icon className="nav-icon" />
                  <span>{item.label}</span>
                </Link>
              );
            })}
          </nav>

          {/* User Menu Section */}
          <div className="header-user">
            {user ? (
              <div className="user-menu" ref={userDropdownRef}>
                <button
                  onClick={toggleUserDropdown}
                  className="user-menu-button"
                  aria-label="Menu do usuário"
                >
                  <div className="user-avatar">
                    <FaUser />
                  </div>
                  <span className="user-name">{user.nome}</span>
                  <FaChevronDown className={`dropdown-arrow ${isUserDropdownOpen ? 'rotated' : ''}`} />
                </button>
                
                {isUserDropdownOpen && (
                  <div className="user-dropdown">
                    <div className="dropdown-header">
                      <div className="dropdown-user-info">
                        <div className="dropdown-avatar">
                          <FaUser />
                        </div>
                        <div>
                          <div className="dropdown-name">{user.nome}</div>
                          <div className="dropdown-email">{user.email}</div>
                        </div>
                      </div>
                    </div>
                    
                    <div className="dropdown-links">
                      <Link to="/dashboard" className="dropdown-link">
                        <FaChartBar />
                        <span>Dashboard</span>
                      </Link>
                      <Link to="/my-properties" className="dropdown-link">
                        <FaBuilding />
                        <span>Meus Imóveis</span>
                      </Link>
                      <Link to="/add-property" className="dropdown-link">
                        <FaPlus />
                        <span>Anunciar</span>
                      </Link>
                      <Link to="/statistics" className="dropdown-link">
                        <FaChartBar />
                        <span>Estatísticas</span>
                      </Link>
                    </div>
                    
                    <div className="dropdown-divider"></div>
                    
                    <button
                      onClick={handleLogout}
                      className="dropdown-link dropdown-logout"
                    >
                      <FaSignOutAlt />
                      <span>Sair</span>
                    </button>
                  </div>
                )}
              </div>
            ) : (
              <div className="auth-buttons">
                <Link to="/login" className="auth-button auth-button-secondary">
                  <FaUser />
                  <span>Entrar</span>
                </Link>
                <Link to="/register" className="auth-button auth-button-primary">
                  <span>Cadastrar</span>
                </Link>
              </div>
            )}
          </div>

          {/* Mobile Menu Toggle Button */}
          <div className="mobile-menu-toggle">
            <button
              onClick={toggleMenu}
              className="mobile-toggle-button"
              aria-label="Alternar menu"
            >
              {isMenuOpen ? <FaTimes /> : <FaBars />}
            </button>
          </div>
        </div>

        {/* Mobile Navigation Menu */}
        {isMenuOpen && (
          <div className="mobile-menu">
            <nav className="mobile-nav">
              
              {/* Main Navigation Links */}
              {navigationItems.map((item) => {
                const Icon = item.icon;
                return (
                  <Link 
                    key={item.path}
                    to={item.path} 
                    className={`mobile-nav-item ${isActive(item.path) ? 'mobile-nav-item-active' : ''}`}
                    onClick={toggleMenu}
                  >
                    <Icon className="mobile-nav-icon" />
                    <span>{item.label}</span>
                  </Link>
                );
              })}
              
              {/* User-specific Mobile Navigation */}
              {user && userNavigationItems.map((item) => {
                const Icon = item.icon;
                return (
                  <Link 
                    key={item.path}
                    to={item.path} 
                    className={`mobile-nav-item ${isActive(item.path) ? 'mobile-nav-item-active' : ''}`}
                    onClick={toggleMenu}
                  >
                    <Icon className="mobile-nav-icon" />
                    <span>{item.label}</span>
                  </Link>
                );
              })}
              
              {/* Mobile User Menu */}
              {user ? (
                <div className="mobile-user-section">
                  <div className="mobile-user-info">
                    <div className="mobile-user-avatar">
                      <FaUser />
                    </div>
                    <div>
                      <div className="mobile-user-name">{user.nome}</div>
                      <div className="mobile-user-email">{user.email}</div>
                    </div>
                  </div>
                  
                  <button
                    onClick={() => {
                      handleLogout();
                      toggleMenu();
                    }}
                    className="mobile-logout-button"
                  >
                    <FaSignOutAlt />
                    <span>Sair</span>
                  </button>
                </div>
              ) : (
                <div className="mobile-auth-section">
                  <Link 
                    to="/login" 
                    className="mobile-auth-button mobile-auth-button-secondary" 
                    onClick={toggleMenu}
                  >
                    <FaUser />
                    <span>Entrar</span>
                  </Link>
                  
                  <Link 
                    to="/register" 
                    className="mobile-auth-button mobile-auth-button-primary" 
                    onClick={toggleMenu}
                  >
                    <span>Cadastrar</span>
                  </Link>
                </div>
              )}
            </nav>
          </div>
        )}
      </div>
    </header>
  );
};

export default Header; 