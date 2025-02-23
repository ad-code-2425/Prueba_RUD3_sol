# H1 Respostas ás preguntas da Tarefa03.1
2- mvn clean generate-sources
14- Engadindo cascade = CascadeType.REMOVE nos getters de Account.java: 

@OneToMany(fetch=FetchType.LAZY, mappedBy="accountByAccountDestId", cascade = CascadeType.REMOVE)
    public Set<AccMovement> getAccMovementsForAccountDestId() {
        return this.accMovementsForAccountDestId;
    }   
   

@OneToMany(fetch=FetchType.LAZY, mappedBy="accountByAccountOriginId", cascade =  CascadeType.REMOVE)
    public Set<AccMovement> getAccMovementsForAccountOriginId() {
        return this.accMovementsForAccountOriginId;
    }
    
