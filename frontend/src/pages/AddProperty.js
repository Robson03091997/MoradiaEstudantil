import React, { useState, useRef } from 'react';
import { useNavigate } from 'react-router-dom';
import { useProperty } from '../contexts/PropertyContext';
import { useAuth } from '../contexts/AuthContext';
import { FaSave, FaCamera, FaTrash, FaUpload } from 'react-icons/fa';

const AddProperty = () => {
  const { addProperty } = useProperty();
  const { user } = useAuth();
  const navigate = useNavigate();
  const fileInputRef = useRef(null);
  const [loading, setLoading] = useState(false);
  const [success, setSuccess] = useState(false);
  const [error, setError] = useState('');
  const [imagePreview, setImagePreview] = useState(null);
  const [imageError, setImageError] = useState('');
  const [isDragOver, setIsDragOver] = useState(false);

  const [formData, setFormData] = useState({
    tipo: '',
    endereco: '',
    banheiros: 1,
    area: '',
    maxInquilinos: 1,
    valor: '',
    imagem: '',
    // Campos específicos por tipo
    quartos: '',
    garagem: '',
    cozinha: false,
    sacada: false,
    banheiroPrivativo: false,
    arCondicionado: false,
    cozinhaCompartilhada: false,
    internet: false,
    refeicoesInclusas: false,
    limpezaInclusa: false,
    horarioFechamento: false,
    regrasRigorosas: false,
    andares: '',
    kitnets: '',
    compartilhado: false,
    tipoCompartilhamento: ''
  });

  const validateImage = (file) => {
    // Validação do tipo de arquivo
    const validTypes = ['image/jpeg', 'image/jpg', 'image/png', 'image/webp'];
    if (!validTypes.includes(file.type)) {
      setImageError('Por favor, selecione uma imagem nos formatos: JPG, PNG ou WebP');
      return false;
    }

    // Validação do tamanho (máximo 5MB)
    const maxSize = 5 * 1024 * 1024; // 5MB
    if (file.size > maxSize) {
      setImageError('A imagem deve ter no máximo 5MB');
      return false;
    }

    return true;
  };

  const processImage = (file) => {
    if (!validateImage(file)) {
      return;
    }

    // Criar preview
    const reader = new FileReader();
    reader.onload = (e) => {
      setImagePreview(e.target.result);
      setFormData(prev => ({ ...prev, imagem: e.target.result }));
      setImageError('');
    };
    reader.readAsDataURL(file);
  };

  const handleImageChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      processImage(file);
    }
  };

  const handleDragOver = (e) => {
    e.preventDefault();
    setIsDragOver(true);
  };

  const handleDragLeave = (e) => {
    e.preventDefault();
    setIsDragOver(false);
  };

  const handleDrop = (e) => {
    e.preventDefault();
    setIsDragOver(false);
    
    const files = e.dataTransfer.files;
    if (files.length > 0) {
      processImage(files[0]);
    }
  };

  const removeImage = () => {
    setImagePreview(null);
    setFormData(prev => ({ ...prev, imagem: '' }));
    setImageError('');
    if (fileInputRef.current) {
      fileInputRef.current.value = '';
    }
  };

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: type === 'checkbox' ? checked : value
    }));
  };

  const validateForm = () => {
    if (!formData.tipo) {
      setError('Selecione o tipo de imóvel');
      return false;
    }
    if (!formData.endereco.trim()) {
      setError('Endereço é obrigatório');
      return false;
    }
    if (!formData.area || formData.area <= 0) {
      setError('Área deve ser maior que zero');
      return false;
    }
    if (!formData.valor || formData.valor <= 0) {
      setError('Valor deve ser maior que zero');
      return false;
    }
    if (!formData.imagem) {
      setError('Foto do imóvel é obrigatória');
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
      const propertyData = {
        tipo: formData.tipo,
        endereco: formData.endereco.trim(),
        banheiros: parseInt(formData.banheiros),
        area: parseFloat(formData.area),
        maxInquilinos: parseInt(formData.maxInquilinos),
        valor: parseFloat(formData.valor),
        imagem: formData.imagem,
        proprietario: {
          id: user.id,
          nome: user.nome,
          sobrenome: user.sobrenome,
          email: user.email
        },
        compartilhado: formData.compartilhado,
        tipoCompartilhamento: formData.compartilhado ? formData.tipoCompartilhamento : null,
        responsavel: null
      };

      // Adicionar campos específicos por tipo
      switch (formData.tipo) {
        case 'Casa':
          propertyData.quartos = parseInt(formData.quartos);
          propertyData.garagem = parseInt(formData.garagem);
          break;
        case 'República':
          propertyData.quartos = parseInt(formData.quartos);
          propertyData.cozinhaCompartilhada = formData.cozinhaCompartilhada;
          propertyData.internet = formData.internet;
          break;
        case 'Pensionato':
          propertyData.quartos = parseInt(formData.quartos);
          propertyData.refeicoesInclusas = formData.refeicoesInclusas;
          propertyData.limpezaInclusa = formData.limpezaInclusa;
          propertyData.horarioFechamento = formData.horarioFechamento;
          propertyData.regrasRigorosas = formData.regrasRigorosas;
          break;
        case 'Kitnet':
          propertyData.cozinha = formData.cozinha;
          propertyData.sacada = formData.sacada;
          break;
        case 'Quarto':
          propertyData.banheiroPrivativo = formData.banheiroPrivativo;
          propertyData.arCondicionado = formData.arCondicionado;
          break;
        case 'Prédio de Kitnets':
          propertyData.andares = parseInt(formData.andares);
          propertyData.kitnets = parseInt(formData.kitnets);
          break;
        default:
          // Para outros tipos, não adicionar campos específicos
          break;
      }

      const result = addProperty(propertyData);
      
      if (result.success) {
        setSuccess(true);
        setTimeout(() => {
          navigate('/my-properties');
        }, 2000);
      } else {
        setError('Erro ao cadastrar imóvel');
      }
    } catch (err) {
      setError('Erro ao cadastrar imóvel. Tente novamente.');
    } finally {
      setLoading(false);
    }
  };

  const renderSpecificFields = () => {
    switch (formData.tipo) {
      case 'Casa':
        return (
          <>
            <div className="form-group">
              <label className="form-label">Número de Quartos *</label>
              <input
                type="number"
                name="quartos"
                value={formData.quartos}
                onChange={handleChange}
                className="form-input"
                min="1"
                required
              />
            </div>
            <div className="form-group">
              <label className="form-label">Vagas de Garagem</label>
              <input
                type="number"
                name="garagem"
                value={formData.garagem}
                onChange={handleChange}
                className="form-input"
                min="0"
              />
            </div>
          </>
        );
      
      case 'República':
        return (
          <>
            <div className="form-group">
              <label className="form-label">Número de Quartos *</label>
              <input
                type="number"
                name="quartos"
                value={formData.quartos}
                onChange={handleChange}
                className="form-input"
                min="1"
                required
              />
            </div>
            <div className="form-group">
              <label className="flex items-center gap-2">
                <input
                  type="checkbox"
                  name="cozinhaCompartilhada"
                  checked={formData.cozinhaCompartilhada}
                  onChange={handleChange}
                  className="rounded"
                />
                Cozinha Compartilhada
              </label>
            </div>
            <div className="form-group">
              <label className="flex items-center gap-2">
                <input
                  type="checkbox"
                  name="internet"
                  checked={formData.internet}
                  onChange={handleChange}
                  className="rounded"
                />
                Internet Inclusa
              </label>
            </div>
          </>
        );
      
      case 'Pensionato':
        return (
          <>
            <div className="form-group">
              <label className="form-label">Número de Quartos *</label>
              <input
                type="number"
                name="quartos"
                value={formData.quartos}
                onChange={handleChange}
                className="form-input"
                min="1"
                required
              />
            </div>
            <div className="form-group">
              <label className="flex items-center gap-2">
                <input
                  type="checkbox"
                  name="refeicoesInclusas"
                  checked={formData.refeicoesInclusas}
                  onChange={handleChange}
                  className="rounded"
                />
                Refeições Inclusas
              </label>
            </div>
            <div className="form-group">
              <label className="flex items-center gap-2">
                <input
                  type="checkbox"
                  name="limpezaInclusa"
                  checked={formData.limpezaInclusa}
                  onChange={handleChange}
                  className="rounded"
                />
                Limpeza Inclusa
              </label>
            </div>
            <div className="form-group">
              <label className="flex items-center gap-2">
                <input
                  type="checkbox"
                  name="horarioFechamento"
                  checked={formData.horarioFechamento}
                  onChange={handleChange}
                  className="rounded"
                />
                Horário de Fechamento
              </label>
            </div>
            <div className="form-group">
              <label className="flex items-center gap-2">
                <input
                  type="checkbox"
                  name="regrasRigorosas"
                  checked={formData.regrasRigorosas}
                  onChange={handleChange}
                  className="rounded"
                />
                Regras Rigorosas
              </label>
            </div>
          </>
        );
      
      case 'Kitnet':
        return (
          <>
            <div className="form-group">
              <label className="flex items-center gap-2">
                <input
                  type="checkbox"
                  name="cozinha"
                  checked={formData.cozinha}
                  onChange={handleChange}
                  className="rounded"
                />
                Tem Cozinha
              </label>
            </div>
            <div className="form-group">
              <label className="flex items-center gap-2">
                <input
                  type="checkbox"
                  name="sacada"
                  checked={formData.sacada}
                  onChange={handleChange}
                  className="rounded"
                />
                Tem Sacada
              </label>
            </div>
          </>
        );
      
      case 'Quarto':
        return (
          <>
            <div className="form-group">
              <label className="flex items-center gap-2">
                <input
                  type="checkbox"
                  name="banheiroPrivativo"
                  checked={formData.banheiroPrivativo}
                  onChange={handleChange}
                  className="rounded"
                />
                Banheiro Privativo
              </label>
            </div>
            <div className="form-group">
              <label className="flex items-center gap-2">
                <input
                  type="checkbox"
                  name="arCondicionado"
                  checked={formData.arCondicionado}
                  onChange={handleChange}
                  className="rounded"
                />
                Ar Condicionado
              </label>
            </div>
          </>
        );
      
      case 'Prédio de Kitnets':
        return (
          <>
            <div className="form-group">
              <label className="form-label">Número de Andares *</label>
              <input
                type="number"
                name="andares"
                value={formData.andares}
                onChange={handleChange}
                className="form-input"
                min="1"
                required
              />
            </div>
            <div className="form-group">
              <label className="form-label">Número de Kitnets *</label>
              <input
                type="number"
                name="kitnets"
                value={formData.kitnets}
                onChange={handleChange}
                className="form-input"
                min="1"
                required
              />
            </div>
          </>
        );
      
      default:
        return null;
    }
  };

  return (
    <div className="container py-8">
      <div className="max-w-4xl mx-auto">
        {/* Header */}
        <div className="mb-8">
          <h1 className="text-3xl font-bold text-gray-900 mb-2">
            Anunciar Novo Imóvel
          </h1>
          <p className="text-gray-600">
            Preencha as informações do seu imóvel para começar a receber interessados
          </p>
        </div>

        {success && (
          <div className="success-message">
            Imóvel cadastrado com sucesso! Redirecionando...
          </div>
        )}

        {error && (
          <div className="error-message">
            {error}
          </div>
        )}

        <form onSubmit={handleSubmit} className="space-y-8">
          {/* Basic Information */}
          <div className="card">
            <h2 className="text-xl font-semibold mb-6">Informações Básicas</h2>
            
            <div className="grid grid-2 gap-6">
              <div className="form-group">
                <label className="form-label">Tipo de Imóvel *</label>
                <select
                  name="tipo"
                  value={formData.tipo}
                  onChange={handleChange}
                  className="form-select"
                  required
                >
                  <option value="">Selecione o tipo</option>
                  <option value="Casa">Casa</option>
                  <option value="Kitnet">Kitnet</option>
                  <option value="Quarto">Quarto</option>
                  <option value="República">República</option>
                  <option value="Pensionato">Pensionato</option>
                  <option value="Edícula">Edícula</option>
                  <option value="Prédio de Kitnets">Prédio de Kitnets</option>
                </select>
              </div>

              <div className="form-group">
                <label className="form-label">Endereço Completo *</label>
                <input
                  type="text"
                  name="endereco"
                  value={formData.endereco}
                  onChange={handleChange}
                  className="form-input"
                  placeholder="Rua, número, bairro, cidade"
                  required
                />
              </div>

              <div className="form-group">
                <label className="form-label">Número de Banheiros *</label>
                <input
                  type="number"
                  name="banheiros"
                  value={formData.banheiros}
                  onChange={handleChange}
                  className="form-input"
                  min="1"
                  required
                />
              </div>

              <div className="form-group">
                <label className="form-label">Área (m²) *</label>
                <input
                  type="number"
                  name="area"
                  value={formData.area}
                  onChange={handleChange}
                  className="form-input"
                  min="1"
                  step="0.1"
                  required
                />
              </div>

              <div className="form-group">
                <label className="form-label">Capacidade Máxima (pessoas) *</label>
                <input
                  type="number"
                  name="maxInquilinos"
                  value={formData.maxInquilinos}
                  onChange={handleChange}
                  className="form-input"
                  min="1"
                  required
                />
              </div>

              <div className="form-group">
                <label className="form-label">Valor do Aluguel (R$) *</label>
                <input
                  type="number"
                  name="valor"
                  value={formData.valor}
                  onChange={handleChange}
                  className="form-input"
                  min="0"
                  step="0.01"
                  required
                />
              </div>
            </div>
          </div>

          {/* Specific Fields */}
          {formData.tipo && (
            <div className="card">
              <h2 className="text-xl font-semibold mb-6">Características Específicas</h2>
              <div className="grid grid-2 gap-6">
                {renderSpecificFields()}
              </div>
            </div>
          )}

          {/* Sharing Options */}
          <div className="card">
            <h2 className="text-xl font-semibold mb-6">Opções de Compartilhamento</h2>
            
            <div className="form-group">
              <label className="flex items-center gap-2">
                <input
                  type="checkbox"
                  name="compartilhado"
                  checked={formData.compartilhado}
                  onChange={handleChange}
                  className="rounded"
                />
                Este imóvel é compartilhado
              </label>
            </div>

            {formData.compartilhado && (
              <div className="form-group">
                <label className="form-label">Tipo de Compartilhamento</label>
                <select
                  name="tipoCompartilhamento"
                  value={formData.tipoCompartilhamento}
                  onChange={handleChange}
                  className="form-select"
                >
                  <option value="">Selecione</option>
                  <option value="MASCULINO">Masculino</option>
                  <option value="FEMININO">Feminino</option>
                  <option value="MISTO">Misto</option>
                </select>
              </div>
            )}
          </div>

          {/* Image Upload */}
          <div className="card">
            <h2 className="text-xl font-semibold mb-6">Foto do Imóvel *</h2>
            
            <div className="image-upload-section">
              {!imagePreview ? (
                <div 
                  className={`image-upload-area ${isDragOver ? 'drag-over' : ''}`}
                  onClick={() => fileInputRef.current?.click()} 
                  onDragOver={handleDragOver} 
                  onDragLeave={handleDragLeave} 
                  onDrop={handleDrop}
                >
                  <div className="image-upload-content">
                    <FaCamera className="image-upload-icon" />
                    <h3 className="image-upload-title">
                      {isDragOver ? 'Solte a imagem aqui' : 'Adicionar Foto'}
                    </h3>
                    <p className="image-upload-description">
                      {isDragOver 
                        ? 'Solte a imagem para fazer o upload' 
                        : 'Clique para selecionar uma imagem ou arraste e solte aqui'
                      }
                    </p>
                    <div className="image-upload-formats">
                      Formatos aceitos: JPG, PNG, WebP (máx. 5MB)
                    </div>
                  </div>
                  <input
                    ref={fileInputRef}
                    type="file"
                    accept="image/jpeg,image/jpg,image/png,image/webp"
                    onChange={handleImageChange}
                    className="image-upload-input"
                  />
                </div>
              ) : (
                <div className="image-preview-container">
                  <div className="image-preview-wrapper">
                    <img
                      src={imagePreview}
                      alt="Preview do imóvel"
                      className="image-preview"
                    />
                    <div className="image-preview-overlay">
                      <button
                        type="button"
                        onClick={removeImage}
                        className="image-remove-button"
                        title="Remover imagem"
                      >
                        <FaTrash />
                      </button>
                    </div>
                  </div>
                  <div className="image-preview-actions">
                    <button
                      type="button"
                      onClick={() => fileInputRef.current?.click()}
                      className="image-change-button"
                    >
                      <FaUpload />
                      Trocar Imagem
                    </button>
                    <input
                      ref={fileInputRef}
                      type="file"
                      accept="image/jpeg,image/jpg,image/png,image/webp"
                      onChange={handleImageChange}
                      className="image-upload-input"
                    />
                  </div>
                </div>
              )}

              {imageError && (
                <div className="image-error-message">
                  <FaTrash />
                  {imageError}
                </div>
              )}
            </div>
          </div>

          {/* Submit */}
          <div className="flex justify-end gap-4">
            <button
              type="button"
              onClick={() => navigate('/my-properties')}
              className="btn btn-secondary"
            >
              Cancelar
            </button>
            <button
              type="submit"
              disabled={loading}
              className="btn btn-primary"
            >
              {loading ? (
                <>
                  <div className="spinner w-4 h-4"></div>
                  Cadastrando...
                </>
              ) : (
                <>
                  <FaSave />
                  Cadastrar Imóvel
                </>
              )}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default AddProperty; 