package pl.macieksob.rentCar.model;

public enum Place {

    FIRST("Białystok, ul. Czesława Miłosza 2, Atrium Biała(parking podziemny)"),
    SECOND("Białystok, ul. Wrocławska 20, Galeria Zielone Wzgórza(parking podziemny)"),
    THIRD("Białystok, ul. aleja Jana Pawła II 92, Makro");

    private String name;
    Place() {
    }

    Place(String name) {
        this.name = name;
    }

    public String showPlace() {

        return name;
    }
}