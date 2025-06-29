import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { AuthProvider } from './contexts/AuthContext';
import { PropertyProvider } from './contexts/PropertyContext';
import Header from './components/Header';
import Footer from './components/Footer';
import Home from './pages/Home';
import Login from './pages/Login';
import Register from './pages/Register';
import Dashboard from './pages/Dashboard';
import PropertyList from './pages/PropertyList';
import PropertyDetail from './pages/PropertyDetail';
import AddProperty from './pages/AddProperty';
import MyProperties from './pages/MyProperties';
import Statistics from './pages/Statistics';
import './App.css';

function App() {
  return (
    <Router>
      <AuthProvider>
        <PropertyProvider>
          <div className="app">
            <Header />
            <main className="main-content">
              <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
                <Route path="/dashboard" element={<Dashboard />} />
                <Route path="/properties" element={<PropertyList />} />
                <Route path="/properties/:id" element={<PropertyDetail />} />
                <Route path="/add-property" element={<AddProperty />} />
                <Route path="/my-properties" element={<MyProperties />} />
                <Route path="/statistics" element={<Statistics />} />
              </Routes>
            </main>
            <Footer />
          </div>
        </PropertyProvider>
      </AuthProvider>
    </Router>
  );
}

export default App; 