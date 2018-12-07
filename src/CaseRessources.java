public class CaseRessources {
    private Ressources r;
    private int nombre;

    public CaseRessources(Ressources r)
    {
        this.r = r;
        this.nombre = 0;
    }

    public CaseRessources(Ressources r, int nombre)
    {
        this.r = r;
        this.nombre = nombre;
    }

    public void plusNombre()
    {
        nombre++;
    }

    public void moinsNombre()
    {
        if(this.nombre > 0)
            nombre--;

    }

    public void afficher()
    {
        System.out.println("Ressource : " + this.r + " " +this.nombre);
    }

    public String toString()
    {
        return "" + this.r + " "+ this.nombre + "\n";
    }
    public Ressources getRessource()
    {
        return this.r;
    }

    public int getNombre()
    {
        return this.nombre;
    }

    public boolean equals(CaseRessources c)
    {
        return this.r.equals(c.getRessource());
    }
}
