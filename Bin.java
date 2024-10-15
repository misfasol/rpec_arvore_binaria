class Main {
    public static void main(String[] args) {
        Arvore teste = new Arvore();
        teste.inserir(5);
        teste.inserir(10);
        teste.inserir(7);
        teste.inserir(6);
        teste.inserir(8);
        teste.inserir(9);
        teste.printInOrdem();
        int rem = 10;
        System.out.println("remo: " + rem);
        teste.remover(rem);
        teste.printInOrdem();


        // Arvore ar = new Arvore();
        // // 14, 15, 4, 9, 7, 18, 3, 5, 16, 20, 17, 9, 5
        // ar.inserir(14);
        // ar.inserir(15);
        // ar.inserir(4);
        // ar.inserir(9);
        // ar.inserir(7);
        // ar.inserir(18);
        // ar.inserir(3);
        // ar.inserir(5);
        // ar.inserir(16);
        // ar.inserir(20);
        // ar.inserir(17);
        // ar.inserir(2);
        // ar.printInOrdem();
        // int rem = 14;
        // System.out.println("Removendo " + rem);
        // ar.remover(rem);
        // ar.printInOrdem();
    }
}

class Arvore {
    private Node raiz;
    public Arvore() {
        raiz = null;
    }

    public Node getRaiz() {
        return raiz;
    }

    public void inserir(int i) {
        if (raiz == null) {
            raiz = new Node(i);
            return;
        }
        inserir(raiz, i);
    }
    private void inserir(Node n, int i) {
        if (i < n.num) {
            if (n.esq == null) {
                n.esq = new Node(i);
            } else {
                inserir(n.esq, i);
            }
        } else {
            if (n.dir == null) {
                n.dir = new Node(i);
            } else {
                inserir(n.dir, i);
            }
        }
    }

    public RetornoRemocao removerMaior() {
        return removerMaior(raiz);
    }

    public RetornoRemocao removerMaior(Node n) {
        if (n == null) return RetornoRemocao.ERRO_NULL;
        if (n.dir == null) {
            n = n.esq;
            return RetornoRemocao.ERRO_MENOR_PROPRIO;
        }
        Node atual = n;
        while (atual.dir.dir != null) {
            atual = atual.dir;
        }
        if (atual.dir.esq != null) {
            atual.dir = atual.dir.esq;
            return RetornoRemocao.OK;
        }
        atual.dir = null;
        return RetornoRemocao.OK;
    }

    public RetornoRemocao removerMenor() {
        return removerMenor(raiz);
    }

    public RetornoRemocao removerMenor(Node n) {
        if (n == null) return RetornoRemocao.ERRO_NULL;
        if (n.esq == null) {
            n = n.dir;
            return RetornoRemocao.ERRO_MENOR_PROPRIO;
        }
        Node atual = n;
        while (atual.esq.esq != null) {
            atual = atual.esq;
        }
        if (atual.esq.dir != null) {
            atual.esq = atual.esq.dir;
            return RetornoRemocao.OK;
        }
        atual.esq = null;
        return RetornoRemocao.OK;
    }

    public Node getMenor(Node base) {
        if (base == null) return null;
        Node atual = base;
        while (true) {
            if (atual.esq == null) return atual;
            atual = atual.esq;
        }
    }

    public Node getMaior(Node base) {
        if (base == null) return null;
        Node atual = base;
        while (true) {
            if (atual.dir == null) return atual;
            atual = atual.dir;
        }
    }

    public void remover(int i) {
        if (raiz == null) return;
        Node pai = raiz;
        Node exluido = raiz;
        while (true) {
            if (i == exluido.num) break;
            pai = exluido;
            if (i < exluido.num) {
                exluido = exluido.esq;
            } else {
                exluido = exluido.dir;
            }
        }
        if (exluido.dir != null) {
            Node menor = getMenor(exluido.dir);
            RetornoRemocao ret = removerMenor(exluido.dir);
            menor.esq = exluido.esq;
            if (ret != RetornoRemocao.ERRO_MENOR_PROPRIO) menor.dir = exluido.dir;
            if (i < pai.num) {
                pai.esq = menor;
            } else if (i > pai.num) {
                pai.dir = menor;
            } else if (i == pai.num) {
                raiz = menor;
            }
        } else if (exluido.esq != null) {
            Node maior = getMaior(exluido.esq);
            RetornoRemocao ret = removerMaior(exluido.esq);
            maior.dir = exluido.dir;
            if (ret != RetornoRemocao.ERRO_MENOR_PROPRIO) maior.esq = exluido.esq;
            if (i < pai.num) {
                pai.esq = maior;
            } else if (i > pai.num) {
                pai.dir = maior;
            } else if (i == pai.num) {
                raiz = maior;
            }
        } else {
            if (i < pai.num) pai.esq = null;
            else if (i > pai.num) pai.dir = null;
            else raiz = null;
        }
    }

    public void printPreOrdem() {
        if (raiz != null)
            printPreOrdem(raiz);
    }
    private void printPreOrdem(Node n) {
        System.out.println(n.num);
        if (n.esq != null)
            printPreOrdem(n.esq);
        if (n.dir != null)
            printPreOrdem(n.dir);
    }

    public void printInOrdem() {
        if (raiz != null)
            printInOrdem(raiz);
    }
    private void printInOrdem(Node n) {
        if (n.esq != null)
            printInOrdem(n.esq);
        System.out.println(n.num);
        if (n.dir != null)
            printInOrdem(n.dir);
    }

    public void printPosOrdem() {
        if (raiz != null)
            printPosOrdem(raiz);
    }
    private void printPosOrdem(Node n) {
        if (n.esq != null)
            printInOrdem(n.esq);
        if (n.dir != null)
            printInOrdem(n.dir);
        System.out.println(n.num);
    }

    public enum RetornoRemocao {
        OK,
        ERRO_MENOR_PROPRIO,
        ERRO_NULL
    }
}

class Node {
    int num;
    Node esq;
    Node dir;
    public Node(int num) {
        this.num = num;
        esq = null;
        dir = null;
    }
    public String toString() {
        return String.format("%d", num);
    }
}
