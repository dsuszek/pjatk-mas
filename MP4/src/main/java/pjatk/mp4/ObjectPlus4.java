package pjatk.mp4;

import java.util.LinkedList;
import java.util.List;

public abstract class ObjectPlus4 extends ObjectPlusPlus {

    private List<String> rolesXOR = new LinkedList<>();
    public ObjectPlus4() {
        super();
    }

    public void addLink_subset(String roleName, String reverseRoleName, String superRoleName, ObjectPlusPlus targetObject) throws Exception {

        if (isLink(superRoleName, targetObject)) {
            // istnieje powiązanie do dodawanego obiektu w ramach roli nadrzędnej - można utworzyć nowe powiązanie
            addLink(roleName, reverseRoleName, targetObject);
        } else {
            // brak powiązania do dodawanego obiektu w ramach roli nadrzędnej - wyjątek
            throw new Exception("No link to the '" + targetObject + "' object in the '" + superRoleName + "' super role.");
        }
    }

    public void addXorRole(String xorRoleName) {
        rolesXOR.add(xorRoleName);
    }

    public void addLinkXor(String roleName, String reverseRoleName, ObjectPlusPlus targetObject) throws Exception {
        if (rolesXOR.contains(roleName)) {
            // aktualnie dodawana rola jest objęta ograniczeniem XOR

            // sprawdzenie, czy jest już jakieś powiązanie w ramach ról objętych ograniczeniem XOR
            if (isXorLink()) {
                throw new Exception("There is a link for a XOR roles.");
            }

            // jeśli nie ma powiązań, poniżej zostanie dodane powiązanie korzystając z "normalnej" metody z nadklasy
        }
        // dodanie powiązania
        addXorRole(roleName);
        super.addLink(roleName, reverseRoleName, targetObject);
    }

    private boolean isXorLink() throws Exception {
        for (String role : rolesXOR) {
            if (this.anyLink(role)) {
                return true;
            }
        }
        return false;
    }

    private boolean anyLink(String roleName) throws Exception {
        ObjectPlusPlus[] links = super.getLinks(roleName);

        return links.length != 0;
    }
}
