package io.pismo.account.transaction.tranports.api.advice

import io.pismo.account.transaction.domain.exceptions.GenericBusinessException
import io.pismo.account.transaction.domain.exceptions.ResourceExistsException
import io.pismo.account.transaction.domain.exceptions.ResourceNotFoundException
import io.pismo.account.transaction.services.TransactionsService
import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.OffsetDateTime
import javax.persistence.EntityNotFoundException

@ControllerAdvice
class ApiExceptionAdvice : ResponseEntityExceptionHandler() {

    companion object {
        private val logger = LoggerFactory.getLogger(TransactionsService::class.java)
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(ex: ResourceNotFoundException, request: WebRequest): ResponseEntity<Any> {
        val status = HttpStatus.NOT_FOUND
        val message = MessageException()
        message.status = status.value()
        message.titulo = ex.message
        message.dataHora = OffsetDateTime.now()
        logger.info(message.titulo)
        return ResponseEntity(message, status)
    }

    @ExceptionHandler(ResourceExistsException::class)
    fun handleResourceExistsException(ex: ResourceExistsException, request: WebRequest): ResponseEntity<MessageException> {
        val status = HttpStatus.CONFLICT
        val message = MessageException()
        message.status = status.value()
        message.titulo = ex.message
        message.dataHora = OffsetDateTime.now()
        logger.info(message.titulo)
        return ResponseEntity(message, status)
    }

    @ExceptionHandler(GenericBusinessException::class)
    fun handleGenericBusinessException(ex: GenericBusinessException, request: WebRequest): ResponseEntity<MessageException> {
        val status = HttpStatus.CONFLICT
        val message = MessageException()
        message.status = status.value()
        message.titulo = ex.message
        message.dataHora = OffsetDateTime.now()
        logger.warn(message.titulo)
        return ResponseEntity(message, status)
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleGenericException(ex: DataIntegrityViolationException, request: WebRequest): ResponseEntity<MessageException> {
        val status = HttpStatus.BAD_REQUEST
        val message = MessageException()
        message.status = status.value()
        message.titulo = "An error occurred while trying to persist the object, check its data"
        message.dataHora = OffsetDateTime.now()
        logger.error(message.titulo)
        return ResponseEntity(message, status)
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleGenericException(ex: EntityNotFoundException, request: WebRequest): ResponseEntity<MessageException> {
        val status = HttpStatus.BAD_REQUEST
        val message = MessageException()
        message.status = status.value()
        message.titulo = "One or more entitys not found in database"
        message.dataHora = OffsetDateTime.now()
        logger.info(message.titulo)
        return ResponseEntity(message, status)
    }

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val errors = ex.allErrors.map {
                if(it is FieldError){
                    val error = it
                    Error(error.field, error.defaultMessage)
                } else {
                    Error(it.objectName, it.defaultMessage)
                }
            }.toList()

        val message = MessageException(status.value(),
            "One or more fields are invalid, fill in correctly and try again",
            OffsetDateTime.now(),
            errors
        )
        logger.warn(message.titulo)
        return ResponseEntity(message, status)
    }

}