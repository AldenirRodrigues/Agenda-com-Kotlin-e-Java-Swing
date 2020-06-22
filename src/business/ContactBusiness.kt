package business

import entity.ContactEntity
import repository.ContactRepository
import java.lang.Exception

class ContactBusiness {

    private fun validate(name: String, phone: String) {
        if (name.isEmpty()) {
            throw Exception("Nome obrigatório!")
        }
        if (phone.isEmpty()) {
            throw Exception("Telefone é obrigatório!")
        }
    }

    private fun validateDelete(name: String, phone: String) {
        if (name.isEmpty() || phone.isEmpty()) {
            throw Exception("É obrigaório selecionar um contato antes de deletar!")
        }
    }
    fun getContactCountDescription():String{
        val list = getList()
        val retorno = when{
            list.isEmpty() -> "0 contatos"
            list.size == 1 -> "1 contato"
            else -> "${list.size} contatos"
        }
        return retorno
    }

    fun save(name: String, phone: String) {
        validate(name, phone)

        val contactEntity = ContactEntity(name, phone)
        ContactRepository.save(contactEntity)


    }

    fun delete(name: String, phone: String) {
        validateDelete(name, phone)

        val contactEntity = ContactEntity(name, phone)
        ContactRepository.delete(contactEntity)

    }


    fun getList(): List<ContactEntity> {
        return ContactRepository.getList()
    }
}