package sidekick;

public class CovidData {
    private String country, newConfirmed, TotalConfirmed, TotalRecovered;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNewConfirmed() {
        return newConfirmed;
    }

    public void setNewConfirmed(String newConfirmed) {
        this.newConfirmed = newConfirmed;
    }

    public String getTotalConfirmed() {
        return TotalConfirmed;
    }

    public void setTotalConfirmed(String totalConfirmed) {
        TotalConfirmed = totalConfirmed;
    }

    public String getTotalRecovered() {
        return TotalRecovered;
    }

    public void setTotalRecovered(String totalRecovered) {
        TotalRecovered = totalRecovered;
    }

    public CovidData(String country, String newConfirmed, String totalConfirmed, String totalRecovered) {
        this.country = country;
        this.newConfirmed = newConfirmed;
        TotalConfirmed = totalConfirmed;
        TotalRecovered = totalRecovered;
    }
}
