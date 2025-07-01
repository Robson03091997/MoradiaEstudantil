import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';
import { FaUser, FaEnvelope, FaLock, FaPhone, FaEye, FaEyeSlash } from 'react-icons/fa';

const Register = () => {
  const { register } = useAuth();
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    nome: '',
    sobrenome: '',
    email: '',
    senha: '',
    confirmarSenha: '',
    telefone: ''
  });
  const [showPassword, setShowPassword] = useState(false);
  const [showConfirmPassword, setShowConfirmPassword] = useState(false);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
    setError(''); // Limpar erro quando usuário digita
  };

  const validateForm = () => {
    if (formData.senha !== formData.confirmarSenha) {
      setError('As senhas não coincidem');
      return false;
    }
    if (formData.senha.length < 6) {
      setError('A senha deve ter pelo menos 6 caracteres');
      return false;
    }
    if (!formData.nome.trim() || !formData.sobrenome.trim()) {
      setError('Nome e sobrenome são obrigatórios');
      return false;
    }
    if (!formData.email.trim()) {
      setError('Email é obrigatório');
      return false;
    }
    return true;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');

    if (!validateForm()) {
      setLoading(false);
      return;
    }

    try {
      const userData = {
        nome: formData.nome.trim(),
        sobrenome: formData.sobrenome.trim(),
        email: formData.email.trim(),
        senha: formData.senha,
        telefone: formData.telefone.trim()
      };

      const result = register(userData);
      
      if (result.success) {
        navigate('/dashboard');
      } else {
        setError(result.message);
      }
    } catch (err) {
      setError('Erro ao criar conta. Tente novamente.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-50 py-12 px-4 sm:px-6 lg:px-8">
      <div className="max-w-md w-full space-y-8">
        <div>
          <div className="mx-auto h-12 w-12 flex items-center justify-center rounded-full bg-blue-100">
            <FaUser className="h-6 w-6 text-blue-600" />
          </div>
          <h2 className="mt-6 text-center text-3xl font-extrabold text-gray-900">
            Crie sua conta
          </h2>
          <p className="mt-2 text-center text-sm text-gray-600">
            Ou{' '}
            <Link to="/login" className="font-medium text-blue-600 hover:text-blue-500">
              faça login se já tem uma conta
            </Link>
          </p>
        </div>

        <form className="mt-8 space-y-6" onSubmit={handleSubmit}>
          {error && (
            <div className="error-message">
              {error}
            </div>
          )}

          <div className="space-y-4">
            {/* Nome e Sobrenome */}
            <div className="grid grid-2 gap-4">
              <div className="form-group">
                <label htmlFor="nome" className="form-label">
                  Nome *
                </label>
                <div className="relative">
                  <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                    <FaUser className="h-5 w-5 text-gray-400" />
                  </div>
                  <input
                    id="nome"
                    name="nome"
                    type="text"
                    autoComplete="given-name"
                    required
                    value={formData.nome}
                    onChange={handleChange}
                    className="form-input pl-10"
                    placeholder="Seu nome"
                  />
                </div>
              </div>

              <div className="form-group">
                <label htmlFor="sobrenome" className="form-label">
                  Sobrenome *
                </label>
                <input
                  id="sobrenome"
                  name="sobrenome"
                  type="text"
                  autoComplete="family-name"
                  required
                  value={formData.sobrenome}
                  onChange={handleChange}
                  className="form-input"
                  placeholder="Seu sobrenome"
                />
              </div>
            </div>

            {/* Email */}
            <div className="form-group">
              <label htmlFor="email" className="form-label">
                Email *
              </label>
              <div className="relative">
                <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                  <FaEnvelope className="h-5 w-5 text-gray-400" />
                </div>
                <input
                  id="email"
                  name="email"
                  type="email"
                  autoComplete="email"
                  required
                  value={formData.email}
                  onChange={handleChange}
                  className="form-input pl-10"
                  placeholder="seu@email.com"
                />
              </div>
            </div>

            {/* Telefone */}
            <div className="form-group">
              <label htmlFor="telefone" className="form-label">
                Telefone
              </label>
              <div className="relative">
                <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                  <FaPhone className="h-5 w-5 text-gray-400" />
                </div>
                <input
                  id="telefone"
                  name="telefone"
                  type="tel"
                  autoComplete="tel"
                  value={formData.telefone}
                  onChange={handleChange}
                  className="form-input pl-10"
                  placeholder="(11) 99999-9999"
                />
              </div>
            </div>

            {/* Senha */}
            <div className="form-group">
              <label htmlFor="senha" className="form-label">
                Senha *
              </label>
              <div className="relative">
                <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                  <FaLock className="h-5 w-5 text-gray-400" />
                </div>
                <input
                  id="senha"
                  name="senha"
                  type={showPassword ? 'text' : 'password'}
                  autoComplete="new-password"
                  required
                  value={formData.senha}
                  onChange={handleChange}
                  className="form-input pl-10 pr-10"
                  placeholder="Mínimo 6 caracteres"
                />
                <button
                  type="button"
                  className="absolute inset-y-0 right-0 pr-3 flex items-center"
                  onClick={() => setShowPassword(!showPassword)}
                >
                  {showPassword ? (
                    <FaEyeSlash className="h-5 w-5 text-gray-400" />
                  ) : (
                    <FaEye className="h-5 w-5 text-gray-400" />
                  )}
                </button>
              </div>
            </div>

            {/* Confirmar Senha */}
            <div className="form-group">
              <label htmlFor="confirmarSenha" className="form-label">
                Confirmar Senha *
              </label>
              <div className="relative">
                <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                  <FaLock className="h-5 w-5 text-gray-400" />
                </div>
                <input
                  id="confirmarSenha"
                  name="confirmarSenha"
                  type={showConfirmPassword ? 'text' : 'password'}
                  autoComplete="new-password"
                  required
                  value={formData.confirmarSenha}
                  onChange={handleChange}
                  className="form-input pl-10 pr-10"
                  placeholder="Confirme sua senha"
                />
                <button
                  type="button"
                  className="absolute inset-y-0 right-0 pr-3 flex items-center"
                  onClick={() => setShowConfirmPassword(!showConfirmPassword)}
                >
                  {showConfirmPassword ? (
                    <FaEyeSlash className="h-5 w-5 text-gray-400" />
                  ) : (
                    <FaEye className="h-5 w-5 text-gray-400" />
                  )}
                </button>
              </div>
            </div>
          </div>

          <div className="flex items-center">
            <input
              id="terms"
              name="terms"
              type="checkbox"
              required
              className="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded"
            />
            <label htmlFor="terms" className="ml-2 block text-sm text-gray-900">
              Eu concordo com os{' '}
              <button 
                type="button"
                onClick={() => alert('Termos de Uso - Em desenvolvimento')}
                className="text-blue-600 hover:text-blue-500"
              >
                Termos de Uso
              </button>{' '}
              e{' '}
              <button 
                type="button"
                onClick={() => alert('Política de Privacidade - Em desenvolvimento')}
                className="text-blue-600 hover:text-blue-500"
              >
                Política de Privacidade
              </button>
            </label>
          </div>

          <div>
            <button
              type="submit"
              disabled={loading}
              className="btn btn-primary w-full"
            >
              {loading ? 'Criando conta...' : 'Criar Conta'}
            </button>
          </div>

          <div className="text-center">
            <p className="text-sm text-gray-600">
              Já tem uma conta?{' '}
              <Link to="/login" className="font-medium text-blue-600 hover:text-blue-500">
                Faça login aqui
              </Link>
            </p>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Register; 