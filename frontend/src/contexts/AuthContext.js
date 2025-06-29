import React, { createContext, useContext, useState, useEffect } from 'react';

const AuthContext = createContext();

// Dados de exemplo dos usuários (simulando um banco de dados)
const mockUsers = [
  {
    id: 1,
    email: 'joao@email.com',
    senha: '123',
    nome: 'João',
    sobrenome: 'Silva',
    telefone: '(11) 99999-1111'
  },
  {
    id: 2,
    email: 'maria@email.com',
    senha: '456',
    nome: 'Maria',
    sobrenome: 'Santos',
    telefone: '(11) 99999-2222'
  },
  {
    id: 3,
    email: 'carlos@email.com',
    senha: '789',
    nome: 'Carlos',
    sobrenome: 'Oliveira',
    telefone: '(11) 99999-3333'
  }
];

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    // Verificar se há usuário salvo no localStorage
    const savedUser = localStorage.getItem('user');
    if (savedUser) {
      setUser(JSON.parse(savedUser));
    }
    setLoading(false);
  }, []);

  const login = (email, senha) => {
    // Simular busca no banco de dados
    const foundUser = mockUsers.find(
      u => u.email === email && u.senha === senha
    );

    if (foundUser) {
      const userData = {
        id: foundUser.id,
        email: foundUser.email,
        nome: foundUser.nome,
        sobrenome: foundUser.sobrenome,
        telefone: foundUser.telefone
      };
      
      setUser(userData);
      localStorage.setItem('user', JSON.stringify(userData));
      return { success: true };
    } else {
      return { success: false, message: 'Email ou senha incorretos' };
    }
  };

  const register = (userData) => {
    // Verificar se email já existe
    const existingUser = mockUsers.find(u => u.email === userData.email);
    if (existingUser) {
      return { success: false, message: 'Email já cadastrado' };
    }

    // Simular criação de novo usuário
    const newUser = {
      id: mockUsers.length + 1,
      email: userData.email,
      senha: userData.senha,
      nome: userData.nome,
      sobrenome: userData.sobrenome,
      telefone: userData.telefone || ''
    };

    mockUsers.push(newUser);

    const userToSave = {
      id: newUser.id,
      email: newUser.email,
      nome: newUser.nome,
      sobrenome: newUser.sobrenome,
      telefone: newUser.telefone
    };

    setUser(userToSave);
    localStorage.setItem('user', JSON.stringify(userToSave));
    
    return { success: true };
  };

  const logout = () => {
    setUser(null);
    localStorage.removeItem('user');
  };

  const value = {
    user,
    login,
    register,
    logout,
    loading
  };

  return (
    <AuthContext.Provider value={value}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth deve ser usado dentro de um AuthProvider');
  }
  return context;
}; 