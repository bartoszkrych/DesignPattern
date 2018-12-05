package classes;


public class Meal
{
    double d_protein;
    double d_carbohydrates;
    double d_fat;
    double d_kcal;

    String s_comment;

    final int fi_g_fat = 9;
    final int fi_g_carbo = 4;
    final int fi_g_protein = 4;

    public Meal(double dProtein, double dCarbohydrates, double dFat)
    {
        this.d_protein = dProtein;
        this.d_carbohydrates = dCarbohydrates;
        this.d_fat = dFat;
        this.s_comment = "";
        vSetKcal();
    }

    public Meal(double dProtein, double dCarbohydrates, double dFat, String sComment)
    {
        this.d_protein = dProtein;
        this.d_carbohydrates = dCarbohydrates;
        this.d_fat = dFat;
        this.s_comment = sComment;
        vSetKcal();
    }

    private void vSetKcal()
    {
        d_kcal = d_carbohydrates*fi_g_carbo+d_fat*fi_g_fat+d_protein*fi_g_protein;
    }

    public double dGetProtein()
    {
        return d_protein;
    }

    public double dGetCarbohydrates()
    {
        return d_carbohydrates;
    }

    public double dGetFat()
    {
        return d_fat;
    }

    public double dGetKcal()
    {
        return d_kcal;
    }
}
