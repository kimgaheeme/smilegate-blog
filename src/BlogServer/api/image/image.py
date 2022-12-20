import boto3
from botocore.exceptions import ClientError

client_s3 = boto3.client(
    's3',
    aws_access_key_id="AKIATG4NLTMCN5QJ3NUA",
    aws_secret_access_key="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"
)

"""
upload file to S3
"""


def upload_file(location, file):
    try:
        client_s3.upload_file(
            location,
            "smilegateblogbucket",
            file,
            ExtraArgs={'ContentType': 'image/jpeg'}
        )
    except ClientError as e:
        print(f'Credential error => {e}')
    except Exception as e:
        print(f"Another error => {e}")
