import org.junit.Test;
import pjatk.mp2.Region;
import pjatk.mp2.Unit;

public class TestComposition {
    @Test
    public void ShouldRemoveAllUnitsWhenRegionIsDeleted() {
        Region region1 = new Region("Northern");
        Unit unit1 = new Unit(1, "Unit1");
        Unit unit2 = new Unit(2, "Unit2");

        try {
            region1.addPart("region", "unit", unit1);
            region1.addPart("region", "unit", unit2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        region1.removeFromExtent();
    }
}
