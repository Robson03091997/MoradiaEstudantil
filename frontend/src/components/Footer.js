import React from 'react';
import { Link } from 'react-router-dom';
import { FaHome, FaEnvelope, FaPhone, FaMapMarkerAlt } from 'react-icons/fa';

const Footer = () => {
  return (
    <footer className="bg-gray-900 text-white">
      <div className="container py-12">
        <div className="grid grid-4 gap-8">
          {/* Logo e Descrição */}
          <div className="col-span-2 md:col-span-1">
            <div className="flex items-center gap-2 text-xl font-bold text-blue-400 mb-4">
              <FaHome />
              <span>ImóveisPro</span>
            </div>
            <p className="text-gray-300 mb-4">
              Encontre o lugar perfeito para morar. O melhor sistema de locação de imóveis 
              com opções para todos os tipos de moradia.
            </p>
            <div className="flex gap-4">
              <a href="#" className="text-gray-400 hover:text-blue-400 transition-colors">
                <FaEnvelope size={20} />
              </a>
              <a href="#" className="text-gray-400 hover:text-blue-400 transition-colors">
                <FaPhone size={20} />
              </a>
              <a href="#" className="text-gray-400 hover:text-blue-400 transition-colors">
                <FaMapMarkerAlt size={20} />
              </a>
            </div>
          </div>

          {/* Links Rápidos */}
          <div>
            <h3 className="text-lg font-semibold mb-4">Links Rápidos</h3>
            <ul className="space-y-2">
              <li>
                <Link to="/" className="text-gray-300 hover:text-blue-400 transition-colors">
                  Início
                </Link>
              </li>
              <li>
                <Link to="/properties" className="text-gray-300 hover:text-blue-400 transition-colors">
                  Imóveis
                </Link>
              </li>
              <li>
                <Link to="/add-property" className="text-gray-300 hover:text-blue-400 transition-colors">
                  Anunciar
                </Link>
              </li>
              <li>
                <Link to="/statistics" className="text-gray-300 hover:text-blue-400 transition-colors">
                  Estatísticas
                </Link>
              </li>
            </ul>
          </div>

          {/* Tipos de Imóveis */}
          <div>
            <h3 className="text-lg font-semibold mb-4">Tipos de Imóveis</h3>
            <ul className="space-y-2">
              <li>
                <Link to="/properties?tipo=Casa" className="text-gray-300 hover:text-blue-400 transition-colors">
                  Casas
                </Link>
              </li>
              <li>
                <Link to="/properties?tipo=Kitnet" className="text-gray-300 hover:text-blue-400 transition-colors">
                  Kitnets
                </Link>
              </li>
              <li>
                <Link to="/properties?tipo=Quarto" className="text-gray-300 hover:text-blue-400 transition-colors">
                  Quartos
                </Link>
              </li>
              <li>
                <Link to="/properties?tipo=Republica" className="text-gray-300 hover:text-blue-400 transition-colors">
                  Repúblicas
                </Link>
              </li>
              <li>
                <Link to="/properties?tipo=Pensionato" className="text-gray-300 hover:text-blue-400 transition-colors">
                  Pensionatos
                </Link>
              </li>
            </ul>
          </div>

          {/* Contato */}
          <div>
            <h3 className="text-lg font-semibold mb-4">Contato</h3>
            <div className="space-y-3">
              <div className="flex items-center gap-3">
                <FaEnvelope className="text-blue-400" />
                <span className="text-gray-300">contato@imoveispro.com</span>
              </div>
              <div className="flex items-center gap-3">
                <FaPhone className="text-blue-400" />
                <span className="text-gray-300">(11) 99999-9999</span>
              </div>
              <div className="flex items-center gap-3">
                <FaMapMarkerAlt className="text-blue-400" />
                <span className="text-gray-300">São Paulo, SP</span>
              </div>
            </div>
          </div>
        </div>

        {/* Linha divisória */}
        <hr className="border-gray-700 my-8" />

        {/* Copyright */}
        <div className="flex flex-col md:flex-row justify-between items-center">
          <p className="text-gray-400 text-sm">
            © 2024 ImóveisPro. Todos os direitos reservados.
          </p>
          <div className="flex gap-6 mt-4 md:mt-0">
            <a href="#" className="text-gray-400 hover:text-blue-400 text-sm transition-colors">
              Política de Privacidade
            </a>
            <a href="#" className="text-gray-400 hover:text-blue-400 text-sm transition-colors">
              Termos de Uso
            </a>
            <a href="#" className="text-gray-400 hover:text-blue-400 text-sm transition-colors">
              Suporte
            </a>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer; 