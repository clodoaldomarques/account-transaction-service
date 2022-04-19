package io.pismo.account.transaction.domain.entity

enum class OperationType(val operationTypeId: Int, val description : String, val rule: OperationRules) {
    COMPRA_A_VISTA(1, "COMPRA A VISTA", DebitOperation()),
    COMPRA_PARCELADA(2, "COMPRA PARCELADA", DebitOperation()),
    SAQUE(3, "SAQUE", DebitOperation()),
    PAGAMENTO(4, "PAGAMENTO", CreditOperation());

    companion object {
        fun getById(value: Int) = OperationType.values().find { it.operationTypeId == value }
    }
}