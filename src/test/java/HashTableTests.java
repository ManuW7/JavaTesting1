import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HashTableTests {

    @Test
    public void testInsertToEmpty(){
        HashTableOpen table = new HashTableOpen(10);
        table.insert(1);
        assertEquals("Hash CreateNode SetNextNode SetNewHead ", table.breadCrumbs);
    }

    @Test
    public void testInsertToOccupied(){
        HashTableOpen table = new HashTableOpen(10);
        table.insert(1);
        table.clearBreadcrumbs();
        table.insert(1);
        assertEquals("Hash CreateNode SetNextNode SetNewHead ", table.breadCrumbs);
    }

    @Test
    public void testDeleteExistingFirst(){
        HashTableOpen table = new HashTableOpen(10);
        table.insert(1);
        table.insert(11);
        table.clearBreadcrumbs();
        table.delete(11);
        assertEquals("Hash First MoveHead ", table.breadCrumbs);
    }

    @Test
    public void testDeleteExistingNotFirst(){
        HashTableOpen table = new HashTableOpen(10);
        table.insert(1);
        table.insert(11);
        table.clearBreadcrumbs();
        table.delete(1);
        assertEquals("Hash NotFirst CallFind Hash StartFindCycle EndFindCycle Found Deleted ", table.breadCrumbs);
    }


    @Test
    public void testDeleteNotExistingEmptyBucket(){
        HashTableOpen table = new HashTableOpen(10);
        table.insert(1);
        table.insert(11);
        table.clearBreadcrumbs();
        table.delete(12);
        assertEquals("Hash BucketEmpty ", table.breadCrumbs);
    }

    @Test
    public void testDeleteNotExistingNotEmptyBucket(){
        HashTableOpen table = new HashTableOpen(10);
        table.insert(1);
        table.insert(11);
        table.clearBreadcrumbs();
        table.delete(21);
        assertEquals("Hash NotFirst CallFind Hash StartFindCycle EndFindCycle NotFound ", table.breadCrumbs);
    }

    @Test
    public void testFindExistingFirst(){
        HashTableOpen table = new HashTableOpen(10);
        table.insert(1);
        table.insert(11);
        table.clearBreadcrumbs();
        table.find(11);
        assertEquals("Hash First Found ", table.breadCrumbs);
    }

    @Test
    public void testFindExistingNotFirst(){
        HashTableOpen table = new HashTableOpen(10);
        table.insert(1);
        table.insert(11);
        table.clearBreadcrumbs();
        table.find(1);
        assertEquals("Hash NotFirst CallFind Hash StartFindCycle EndFindCycle Found ", table.breadCrumbs);
    }

    @Test
    public void testFindNotExistingEmptyBucket(){
        HashTableOpen table = new HashTableOpen(10);
        table.insert(1);
        table.insert(11);
        table.clearBreadcrumbs();
        table.find(2);
        assertEquals("Hash EmptyBucket ", table.breadCrumbs);
    }

    @Test
    public void testFindNotExistingNotEmptyBucket(){
        HashTableOpen table = new HashTableOpen(10);
        table.insert(1);
        table.insert(11);
        table.clearBreadcrumbs();
        table.find(21);
        assertEquals("Hash NotFirst CallFind Hash StartFindCycle EndFindCycle NotFound ", table.breadCrumbs);
    }

}
